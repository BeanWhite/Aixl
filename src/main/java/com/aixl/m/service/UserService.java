package com.aixl.m.service;


import com.aixl.m.dao.aiDocMapper;
import com.aixl.m.dao.aiUserMapper;
import com.aixl.m.dao.aiUserMsgMapper;
import com.aixl.m.model.aiDoc;
import com.aixl.m.model.aiUser;
import com.aixl.m.model.aiUserMsg;
import com.aixl.m.model.userAdd;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {


    @Autowired
    private RedisUtils<Object> redisUtils;

    @Autowired
    private aiUserMapper userMapper;

    @Autowired
    private aiDocMapper docMapper;

    @Autowired
    private aiUserMsgMapper userMsgMapper;


    private String MSG;

    //设置redis保存时间
    private long keepTime = 24 * 60 * 60 * 2;

    /**
     * 用户登录
     * @param id    用户id
     * @param pwd   用户密码
     * @return
     */
    public ReturnObject<Object> getUser(String id, String pwd) {
        aiUser user = (aiUser) redisUtils.getCache("user=" + id);
        if (user == null) {
            try {
                user = userMapper.selectByPrimaryKey(id);
                if (user != null) {
                    if(user.getAiUserStatus().equals("正常")){
                        redisUtils.setCache("user=" + id, user, keepTime);
                        this.MSG = "登录成功";
                    }else {
                        this.MSG ="账号被禁用";
                    }
                } else {
                    this.MSG = "账号错误";
                }
            } catch (Exception e) {
                this.MSG = "登录失败";
                e.printStackTrace();
                return ReturnUtils.success(this.MSG, null, 0);
            }
        } else {
            if(user.getAiUserStatus().equals("正常"))
                this.MSG = "登录成功";
            else
                this.MSG = "账号被禁用";
        }
        return ReturnUtils.success(this.MSG, this.MSG == "登录成功" ? user.getAiUserType() : null, 1);
    }

    /**
     * 添加一个用户账户
     * 参数为user对象
     * @param string
     * @return
     */
    public ReturnObject<Object> addUser(aiUser string) {

        int a = userMapper.insert(string);
        if (a > 0) {
            //新增成功
        } else {
            //新增失败
        }
        return ReturnUtils.success(a);
    }

    public ReturnObject<Object> addUserWhole(aiUser a, aiUserMsg b){
       int t1 =  userMapper.insert(a);
       if(t1>0){
           int t2 = userMsgMapper.insert(b);
           return ReturnUtils.success(t2);
       }else return ReturnUtils.success(t1);
    }

    /**
     * 修改用户密码
     * 参数为user对象
     * @param string
     * @return
     */
    public ReturnObject<Object> setPassword(String string) {
        aiUser user = JSON.parseObject(string, aiUser.class);
        if(user.getAiUserPwd()==null){
            user.setAiUserPwd("123456");
        }
        int a = userMapper.updateByPrimaryKey(user);
        if (a > 0) {
            //新增成功
        } else {
            //新增失败
        }
        return ReturnUtils.success(a);
    }

    /**
     * 根据用户id删除用户账号，参数为id
     * @param string
     * @return
     */
    public ReturnObject<Object> removeUser(String string) {
        int a = userMapper.deleteByPrimaryKey(string);
        if (a > 0) {
            //新增成功
        } else {
            //新增失败
        }
        return ReturnUtils.success(a);
    }

    /**
     * 按照userAdd模型创建新用户
     * @param m
     * @return
     */
    public ReturnObject<Object> createUser(userAdd m){
        if(m.getUserType().equals("医生")){
            aiDoc doc  = new aiDoc();
            doc.setAiDocId(m.getUserId());
            doc.setAiDocPwd(m.getPassword());
            doc.setAiUserType(m.getUserType());
            int a = docMapper.insert(doc);
            return ReturnUtils.success(a);
        }else if(m.getUserType().equals("用户")) {
            aiUser user = new aiUser();
            user.setAiUserPwd(m.getPassword());
            user.setAiUserId(m.getUserId());
            user.setAiUserType(m.getUserType());
            int a = userMapper.insert(user);
            return ReturnUtils.success(a);
        }else {
            return ReturnUtils.success(0);
        }
    }

    /**
     * 使用userAdd模型删除账户
     * @return
     */
    public ReturnObject<Object> deleteUser(userAdd m){
        if(m.getUserType().equals("医生")){
            int a = docMapper.deleteByPrimaryKey(m.getUserId());
            return ReturnUtils.success(a);
        }else if(m.getUserType().equals("用户")) {
            int a = userMapper.deleteByPrimaryKey(m.getUserId());
            return ReturnUtils.success(a);
        }else {
            return ReturnUtils.success(0);
        }
    }


    public ReturnObject<Object> getUserStatus(Integer a,Integer b){
        PageHelper.startPage(a,b);
        Page<aiUser> page = userMapper.getStatus();
        return ReturnUtils.success(page.getTotal(),page);
    }

    public ReturnObject<Object> getUserStatusById(aiUser a){
        return ReturnUtils.success(userMapper.getStatusById(a));
    }

    /**
     * 设置用户状态
     * @param id        用户id
     * @param status    状态值
     * @return
     */
    public ReturnObject<Object> setUserStatusById(String id,String status){
        int  a = userMapper.setStatusById(id,status);
        if(a>0){
            redisUtils.clear("user="+id);
        }
        return ReturnUtils.success(a);
    }

}
