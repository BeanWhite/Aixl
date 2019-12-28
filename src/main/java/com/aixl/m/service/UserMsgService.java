package com.aixl.m.service;


import com.aixl.m.dao.aiUserMsgMapper;
import com.aixl.m.model.aiUserMsg;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserMsgService {

    @Autowired
    private RedisUtils<Object> redisUtils;

    @Autowired
    private aiUserMsgMapper userMsgMapper;

    /**
     * 根据用户名或id号查询用户
     *
     * @param parameter
     * @return
     */
    public ReturnObject<Object> getUserMsgForDoc(String parameter) {
        List<aiUserMsg> userMsgList = (List<aiUserMsg>) redisUtils.getCache("user=" + parameter);
        if (userMsgList != null) {
            return ReturnUtils.success("1", userMsgList);
        } else {
            try {
                userMsgList = userMsgMapper.selectMsgForDoc(parameter);
                redisUtils.setCache("user=" + parameter, userMsgList);
                return ReturnUtils.success("1", userMsgList);
            } catch (Exception e) {
                e.printStackTrace();
                return ReturnUtils.success("0", "没有内容");
            }
        }
    }
}
