package com.aixl.m.service;


import com.aixl.m.dao.aiUserMapper;
import com.aixl.m.model.aiUser;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private RedisUtils<Object> redisUtils;

    @Autowired
    private aiUserMapper userMapper;

    private String MSG;

    //设置redis保存时间
    private long keepTime = 24*60*60*2;

    /**
     * 用户登录
     * @param id
     * @param pwd
     * @return
     */
    public ReturnObject<Object> getUser(String id, String pwd){
        aiUser user = (aiUser) redisUtils.getCache(id);
        if(user == null){
            try {
                user = userMapper.selectByPrimaryKey(id);
                if(user!=null){
                    redisUtils.setCache(id,user,keepTime);
                    if(user.getAiUserPwd().equals(pwd))
                        this.MSG ="登录成功";
                    else
                        this.MSG = "账号或密码错误";

                }else {
                    this.MSG = "账号或密码错误";
                }

            }catch (Exception e){
                this.MSG = "登录失败";
                e.printStackTrace();
                return ReturnUtils.success(this.MSG,null,0);
            }
        }else {
           if(user.getAiUserPwd().equals(pwd)){
               this.MSG = "登录成功";
           }else {
               this.MSG = "账号或密码错误";
           }

        }
        return ReturnUtils.success(this.MSG,this.MSG=="登录成功"?user.getAiUserType():null,1);
    }
}
