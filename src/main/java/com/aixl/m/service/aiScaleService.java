package com.aixl.m.service;

import com.aixl.m.dao.aiScaleMapper;
import com.aixl.m.dao.aiTestMapper;
import com.aixl.m.model.aiScale;
import com.aixl.m.model.aiTest;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class aiScaleService {

    @Autowired
    aiScaleMapper scaleMapper;

    @Autowired
    aiTestMapper testMapper;

    //设置redis保存时间
    private long keepTime = 24*60*60*2;

    @Autowired
    RedisUtils<Object> redisUtils;

    /**
     * 获取所有量表信息
     * @return
     */
    public ReturnObject<Object> getAll(){
        List<aiScale> scale =(List<aiScale>) redisUtils.getCache("allScales");
        if(scale!=null){
            return ReturnUtils.success("1",scale) ;
        }
        else {
            try {
                scale = scaleMapper.selectAll();
                if(scale.size()==0)
                    return ReturnUtils.success("1","没有内容");
                else
                    redisUtils.setCache("allScales",scale,keepTime);
                return ReturnUtils.success("1",scale);
            }catch (Exception e){
                return ReturnUtils.success("0","没有内容");
            }
        }
    }

    /**
     * 分页获取量表基本信息
     */
    public ReturnObject<Object> getPages(Integer currentPage,Integer pageSize){
        PageHelper.startPage(currentPage,pageSize);
        Page<aiScale> page = scaleMapper.selectByPages();
        return ReturnUtils.success(page.getTotal(),page);
    }

    /**
     * 将从数据库获取的信息进行处理，分类完成返回给前端,直接返回处理后的结果，前端可以直接使用
     * @param id
     * @return
     */
    public ReturnObject<Object> getScaleTest_n(Integer id){

        List<aiTest> tests =(List<aiTest>)redisUtils.getCache("scaleTestMsg="+id);
            try {
                if(tests==null)
                tests = testMapper.selectByScaleId(id);
                if(tests.size()==0){
                    return ReturnUtils.success("1","没有内容");
                }else {
                    //开始处理
                    for(int m=0;m<tests.size();m++){
                        aiTest t=tests.get(m);
                        //使用getFields()方法可以获取public字段，使用getDeclaredFields()可以获取private和public字段
                        Field[] f1 = t.getClass().getDeclaredFields();

                        for(int i=0;i<f1.length;i++){
                            f1[i].setAccessible(true);
                            if(f1[i].getType().getTypeName()!="java.lang.String"){
                               continue;
                            }

                            String keyName = f1[i].getName();//获取字段名字
                            //将字段名首字母大写方便set和get
                            String methodName=keyName.substring(0,1).toUpperCase()+keyName.substring(1);
                            try{
                                ArrayList<String> arrayList = new ArrayList<>();
                                String str =null;
                                try {
                                    Method method = aiTest.class.getDeclaredMethod("get"+methodName);
                                    str = (String)method.invoke(t);
                                }catch (Exception e){
                                    e.printStackTrace();
                                    continue;
                                }
                                if(str==null)
                                    continue;
                                String reg1 = "&";
                                String reg2 = "\\$";
                                String reg3 = "split;";
                                if(str.contains(reg1)){
                                    List arrayList1 = Arrays.asList(str.split(reg1)) ;
                                    ArrayList A = new ArrayList();
                                    for(int a=0;a<arrayList1.size();a++){
                                        String s = (String) arrayList1.get(a);
                                        if(s.indexOf("$")>-1){
                                            List list = Arrays.asList(s.split(reg2));
                                            ArrayList B = new ArrayList();
                                            for(int b=0;b<list.size();b++) {
                                                String s1 = (String) list.get(b);
                                                if (s1.indexOf(reg3)>-1) {
                                                   B.add(s1.split(reg3));
                                                }else{
                                                    B.add(list.get(b));
                                                    System.out.println(s1);
                                                }
                                            }
                                            A.add(B);
                                        }else{
                                            A.add(arrayList1.get(a));
                                        }
                                    }
                                    //获取类当前属性的setXXX方法（私有和公有方法）
                                    /*Method setMethod=Test.class.getDeclaredMethod("set"+methodName);*/
                                    //获取Test类当前属性的setXXX方法（只能获取公有方法）getMethod
                                   // System.out.println("-----------------");
                                    Method setMethod=aiTest.class.getDeclaredMethod("set"+methodName+"s",Object.class);
                                    Method setMethod_1 = aiTest.class.getDeclaredMethod("set"+methodName,String.class);
                                    //执行该set方法
                                    setMethod.invoke(t,(Object) A);//设置新内容
                                    setMethod_1.invoke(t,"");//删除原始内容
                                    tests.set(m,t);
                                }

                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //处理结束

                    //redisUtils.setCache("scaleTestMsg="+id,tests,keepTime);
                    return ReturnUtils.success("1",tests);
                }
            }catch (Exception e){
                return ReturnUtils.success("0","没有内容");
            }

    }

    /**
     * 根据量表id获取量表中的题目信息
     * @param id
     * @return
     */
    @Deprecated
    public ReturnObject<Object> getScaleTest(Integer id){
        List<aiTest> tests =(List<aiTest>)redisUtils.getCache("scaleTestMsg="+id);
        if(tests==null){
            try {
                tests = testMapper.selectByScaleId(id);
                if(tests.size()==0){
                    return ReturnUtils.success("1","没有内容");
                }else {
                    redisUtils.setCache("scaleTestMsg="+id,tests,keepTime);
                    return ReturnUtils.success("1",tests);
                }
            }catch (Exception e){
                return ReturnUtils.success("0","没有内容");
            }
        }else {
            return ReturnUtils.success("1",tests);
        }
    }


    /**
     * 根据量表id或名称获取信息
     * @param currentPage
     * @param pageSize
     * @param param
     * @return
     */
    public ReturnObject<Object> getTestByIdOrName(Integer currentPage,Integer pageSize, String param){
       PageHelper.startPage(currentPage,pageSize);
       Page<aiTest> page = testMapper.selectByIdOrName(param);
       return ReturnUtils.success(page.getTotal(),page);
    }


    /**
     * 根据量表id和题号（id）获取题目信息
     * @param a
     * @param b
     * @return
     */
    public ReturnObject<Object> getTestByDoubleId(String a,String b){
        return ReturnUtils.success(testMapper.selectByDoubleId(a,b));
    }

    /**
     * 修改题目信息
     * @param test
     * @return
     */
    public ReturnObject<Object> setTest(aiTest test){
        int a =testMapper.setTest(test);
        return ReturnUtils.success(a);
    }

    /**
     * 获取量表所有id
     * @return
     */
    public ReturnObject<Object> getScaleIdList(){
        List<aiScale> list = scaleMapper.getScaleIdList();
        return ReturnUtils.success(list);
    }

    /**
     * 添加题目
     * @param test
     * @return
     */
    public ReturnObject<Object> addTest(aiTest test){
        int a= testMapper.addTest(test);
        System.out.println(test.toString());
        return ReturnUtils.success(a);
    }

    /**
     * 删除题目
     * @param test
     * @return
     */
    public ReturnObject<Object> deleteTest(aiTest test){
        int a = testMapper.deleteTest(test);
        return ReturnUtils.success(a);
    }

}
