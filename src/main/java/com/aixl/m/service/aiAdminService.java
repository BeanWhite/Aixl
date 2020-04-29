package com.aixl.m.service;


import com.aixl.m.dao.aiAdminMapper;

import com.aixl.m.model.aiAdmin;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class aiAdminService {

    // private RedisService redisService;
//设置redis保存时间
    private long keepTime = 24*3600*3;

    @Autowired
    private RedisUtils<Object> redisUtils;

    @Autowired
    private aiAdminMapper adminMapper;

    private String MSG;

    @Async
    public ReturnObject<Object> Test(String id, String pwd){
        //  redisUtils.set("1","id:1,pwd:1");
        aiAdmin admin = (aiAdmin) redisUtils.getCache("admin="+id);
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

        } else {
            try {
                admin = adminMapper.selectByPrimaryKey(id, pwd);
                if (admin == null) {
                    MSG = "账号或密码错误";
                } else{
                    MSG = "登录成功";
                    redisUtils.setCache("admin="+id, admin,keepTime);
                }
            } catch (Exception e) {
                MSG = "账号或密码错误";
                return ReturnUtils.success(this.MSG,null,0);
            }
        }
        return ReturnUtils.success(this.MSG,this.MSG=="登录成功"?admin.getAiUserType():null,1);
    }

    /**
     * 管理员登录（异步）
     * @param id
     * @param pwd
     * @return
     */
    public ReturnObject<Object> getAdmin(String id, String pwd) {
        //  redisUtils.set("1","id:1,pwd:1");
        aiAdmin admin = (aiAdmin) redisUtils.getCache("admin="+id);
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

        } else {
            try {
                admin = adminMapper.selectByPrimaryKey(id, pwd);
                if (admin == null) {
                    MSG = "账号或密码错误";
                } else{
                    MSG = "登录成功";
                    redisUtils.setCache("admin="+id, admin,keepTime);
                }
            } catch (Exception e) {
                MSG = "账号或密码错误";
                return ReturnUtils.success(this.MSG,null,0);
            }
        }
        return ReturnUtils.success(this.MSG,this.MSG=="登录成功"?admin:null,1);
     //  return Test(id,pwd);
    }

    public ReturnObject<Object> updateAdmin(aiAdmin admin){
        adminMapper.updateByPrimaryKey(admin);

        return ReturnUtils.success();
    }

}
