package com.aixl.m.service;


import com.aixl.m.dao.aiDocMapper;
import com.aixl.m.model.aiDoc;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class aiDocService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private aiDocMapper docMapper;

    private String MSG;

    //设置redis保存时间
    private long keepTime = 24*60*60*2;

    /**
     * 医生登录，数据库操作出错时返回“登录失败”。
     * @param id
     * @param pwd
     * @return
     */
    public ReturnObject<Object> getDoc(String id,String pwd){
        aiDoc doc = (aiDoc) redisUtils.getCache("doc="+id);
        if(doc == null){
            try {
                doc = docMapper.selectByPrimaryKey(id);
                if(doc!=null){
                    if(doc.getAiDocPwd().equals(pwd))
                        this.MSG = "登录成功";
                    else
                        this.MSG = "账号或密码错误";
                    redisUtils.setCache("doc="+id,doc,keepTime);
                }else {
                    this.MSG = "账号或密码错误";
                }
            }catch (Exception e){
                this.MSG = "登录失败";
                return ReturnUtils.success(this.MSG,null,0);
            }

        }else {
            if(doc.getAiDocPwd().equals(pwd))
                this.MSG = "登录成功";
            else
                this.MSG = "账号或密码错误";
        }
        return ReturnUtils.success(this.MSG,this.MSG=="登录成功"?doc.getAiUserType():null,1);
    }

    /**
     * 修改医生登录密码
     * 参数为医生对象
     * @param string
     * @return
     */
    public ReturnObject<Object> setPwd(String string){
        aiDoc doc= JSON.parseObject(string,aiDoc.class);
        int a = docMapper.setPwd(doc);
        if(a>0){
            //成功
        }else {
            //失败
        }
        return ReturnUtils.success();
    }

    /**
     * 创建一个医生账户
     * 参数为医生对象
     * 对象只包含医生id，医生密码，医生类型(可以不用)，医生信息（可以不用）
     * @param string
     * @return
     */
    public ReturnObject<Object> addDoc(String string){
        aiDoc doc = JSON.parseObject(string,aiDoc.class);
        int a = docMapper.insert(doc);
        if(a>0){
            //成功
        }else {
            //失败
        }
        return ReturnUtils.success();
    }

    /**
     * 修改医生信息
     * 参数为医生对象
     * 属性中不包含密码和类型
     * @param string
     * @return
     */
    public ReturnObject<Object> setDocMsg(String string){
        aiDoc doc = JSON.parseObject(string,aiDoc.class);
        int a = docMapper.setDocMsg(doc);
        if(a>0){
            //成功
        }else {
            //失败
        }
        return ReturnUtils.success(a);
    }

    /**
     * 删除一个医生账户
     * 参数为医生id号
     * @param string
     * @return
     */
    public ReturnObject<Object> removeDoc(String string){
        int a = docMapper.deleteByPrimaryKey(string);
        if(a>0){
            //成功
        }else {
            //失败
        }
        return ReturnUtils.success();
    }

    /**
     * 重置医生密码
     * @param doc
     * @return
     */
    public ReturnObject<Object> reSetPw(aiDoc doc){
        int a = docMapper.reSetPwd(doc);
        return ReturnUtils.success(a);
    }


    /**
     * 根据医生id获取信息
     * @param doc
     * @return
     */
    public ReturnObject<Object> getDocMsg(String doc){
      return   ReturnUtils.success(docMapper.selectByPrimaryKey(doc));
    }

}
