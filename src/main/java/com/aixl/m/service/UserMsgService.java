package com.aixl.m.service;


import com.aixl.m.dao.aiUserMsgMapper;
import com.aixl.m.model.aiTestHistory;
import com.aixl.m.model.aiUserMsg;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserMsgService {

    //设置redis保存时间
    private long keepTime = 3600*24*3;

    @Autowired
    private RedisUtils<Object> redisUtils;

    @Autowired
    private aiUserMsgMapper userMsgMapper;

    public ReturnObject<Object> getUserMsgsForDoc(Integer currentPage,Integer pageSize){
        try {
            PageHelper.startPage(currentPage, pageSize);
            Page<aiUserMsg> page = userMsgMapper.selectAllByPage();
            return ReturnUtils.success(page.getTotal(),page);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据用户id号查询用户信息
     *
     * @param parameter
     * @return
     */
    public ReturnObject<Object> getUserMsgForDoc(String parameter) {
        aiUserMsg userMsgList = (aiUserMsg) redisUtils.getCache("userMSG=" + parameter);
        if (userMsgList != null) {
            return ReturnUtils.success("1", userMsgList);
        } else {
            try {
                userMsgList = userMsgMapper.selectMsgForDoc(parameter);
                if(userMsgList!=null)
                redisUtils.setCache("userMSG=" + parameter, userMsgList,keepTime);
                return ReturnUtils.success("1",userMsgList);
            } catch (Exception e) {
                e.printStackTrace();
                return ReturnUtils.success("0", "没有内容");
            }
        }
    }


    /**
     * 修改用户信息
     * @param t aiUserMsg对象
     * @return
     */
    public ReturnObject<Object> updateMsgForDoc(aiUserMsg t){

        int a = userMsgMapper.updataMsgForDoc(t);
        redisUtils.setCache("userMSG="+t.getAiUserId(),t,keepTime);
        return  ReturnUtils.success(a);
    }

    /**
     * 新增一个用户
     * @param t
     * @return
     */
    public ReturnObject<Object> addUser(aiUserMsg t){

        int a = userMsgMapper.insert(t);
        if(a>0){
            System.out.println("新增成功");
        }else System.out.println("增加失败");
        return ReturnUtils.success(a);
    }

    /**
     * 删除用户信息
     * @param p
     * @return
     */
    public ReturnObject<Object> deleteUser(String p){
        int a = userMsgMapper.deleteByPrimaryKey(p);
        if(a>0){
            System.out.println("新增成功");
        }else System.out.println("增加失败");
        return ReturnUtils.success(a);
    }
}
