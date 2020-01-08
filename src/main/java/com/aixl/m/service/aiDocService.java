package com.aixl.m.service;


import com.aixl.m.dao.aiDocMapper;
import com.aixl.m.model.aiDoc;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class aiDocService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private aiDocMapper docMapper;

    private String MSG;

    /**
     * 医生登录，数据库操作出错时返回“登录失败”。
     * @param id
     * @param pwd
     * @return
     */
    public ReturnObject<Object> getDoc(String id,String pwd){
        aiDoc doc = (aiDoc) redisUtils.getCache(id);
        if(doc == null){
            try {
                doc = docMapper.selectByPrimaryKey(id);
                if(doc!=null){
                    if(doc.getAiDocPwd().equals(pwd))
                        this.MSG = "登录成功";
                    else
                        this.MSG = "账号或密码错误";
                    redisUtils.setCache(id,doc);
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

}
