package com.aixl.m.service;


import com.aixl.m.dao.aiAdminMapper;

import com.aixl.m.model.aiAdmin;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class aiAdminService {

    // private RedisService redisService;

    @Autowired
    private RedisUtils<Object> redisUtils;

    @Autowired
    private aiAdminMapper adminMapper;

    private String MSG;


    /**
     * 管理员登录（异步）
     * @param id
     * @param pwd
     * @return
     */
    @Async
    public ReturnObject<Object> Test(String id, String pwd){
        //  redisUtils.set("1","id:1,pwd:1");
        aiAdmin admin = (aiAdmin) redisUtils.getCache(id);
        if (admin != null) {
            if(admin.getAiAdminPwd().equals(pwd))
                this.MSG = "登录成功";
            else
                this.MSG = "密码错误";
            //将已登录的用户存入session中

           // Long L= System.currentTimeMillis();
//            Long l;
//            String string;
//            while (true){
//                l = System.currentTimeMillis();
//                Date date = new Date(l);
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM + -dd hh:mm:ss");
//                string=simpleDateFormat.format(date);
//                if(l-L>5000)
//                    break;
//            }
//            Date date = new Date(L);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM + -dd hh:mm:ss");
           // System.out.println("线程"+Thread.currentThread().getName()+"\t"+simpleDateFormat.format(date)+"\t");
            return ReturnUtils.success(this.MSG, this.MSG=="登录成功"?1:0);
        } else {
            try {
                admin = adminMapper.selectByPrimaryKey(id, pwd);
                if (admin == null) {
                    MSG = "账号或密码错误";
                } else{
                    MSG = "登录成功";
                    redisUtils.setCache(id, admin);
                }
            } catch (Exception e) {
                MSG = "账号或密码错误";
            }
            return ReturnUtils.success(this.MSG,(this.MSG=="登录成功"?1:0));
        }
    }

    public ReturnObject<Object> getAdmin(String id, String pwd) {
       return Test(id,pwd);
    }


}
