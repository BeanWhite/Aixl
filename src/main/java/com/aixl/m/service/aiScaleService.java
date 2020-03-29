package com.aixl.m.service;

import com.aixl.m.dao.aiScaleMapper;
import com.aixl.m.dao.aiTestMapper;
import com.aixl.m.model.aiScale;
import com.aixl.m.model.aiTest;
import com.aixl.m.model.aiTestHistory;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 将从数据库获取的信息进行处理，分类完成返回给前端
     * @param id
     * @return
     */
    public ReturnObject<Object> getScaleTest_n(Integer id){

//        List<aiTest> tests =(List<aiTest>)redisUtils.getCache("scaleTestMsg="+id);
//        if(tests==null){
//            try {
//                tests = testMapper.selectByScaleId(id);
//                if(tests.size()==0){
//                    return ReturnUtils.success("1","没有内容");
//                }else {
//                    redisUtils.setCache("scaleTestMsg="+id,tests,keepTime);
//                    return ReturnUtils.success("1",tests);
//                }
//            }catch (Exception e){
//                return ReturnUtils.success("0","没有内容");
//            }
//        }else {
//            return ReturnUtils.success("1",tests);
//        }
        return ReturnUtils.error("暂未开放");
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

    public ReturnObject<Object> deleteTest(aiTest test){
        int a = testMapper.deleteTest(test);
        return ReturnUtils.success(a);
    }

}
