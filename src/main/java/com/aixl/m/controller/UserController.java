package com.aixl.m.controller;


import com.aixl.m.model.userAdd;
import com.aixl.m.service.UserMsgService;
import com.aixl.m.service.UserService;
import com.aixl.m.utils.ReturnObject;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

//使用@PathVariable直接取值，使用@Pathparam 地址栏类似a=b
@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMsgService userMsgService;

    @Autowired
    private UserService userService;

    /**
     * 根据id获取单个用户信息(for-doc)
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/msgForDoc/{str}", method = RequestMethod.GET)
    public ReturnObject<Object> getUserMsgForDoc(@PathVariable(value = "str") String string) {
        return userMsgService.getUserMsgForDoc(string);
    }

    /**
     * 修改单个用户信息（for-doc）
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/Msg/{json}", method = RequestMethod.PUT)
    public ReturnObject<Object> setUserMsgForDoc(@PathVariable(value = "json") String string) {
        return userMsgService.updateMsgForDoc(string);
    }

    /**
     * 新增用户信息
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/Msg/{json}", method = RequestMethod.POST)
    public ReturnObject<Object> addUserMsg(@PathVariable(value = "json") String string) {
        return userMsgService.addUser(string);
    }

    /**
     * 删除用户信息
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/Msg", method = RequestMethod.DELETE)
    public ReturnObject<Object> deleteUserMsg(String string) {
        return userMsgService.deleteUser(string);
    }

    /**
     * 注册一个新的用户
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ReturnObject<Object> addUser(String string) {
        return userService.addUser(string);
    }

    /**
     * 删除一个用户账户
     * 参数为id号
     *
     * @param string
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ReturnObject<Object> removeUser(@PathVariable(value = "id") String string) {
        return userService.removeUser(string);
    }

    /**
     * 重新设置用户密码
     * 参数为user对象
     * @param string
     * @return
     */
    @RequestMapping(value = "/user/{json}",method = RequestMethod.PUT)
    public ReturnObject<Object> setPwd(@PathVariable(value = "json") String string){
        return userService.setPassword(string);
    }


    /**
     * 按照userAdd模型创建新用户，不区分用户类型
     * @param json
     * @return
     */
    @RequestMapping(value = "/both/{json}",method = RequestMethod.POST)
    public ReturnObject<Object> createUser(@PathVariable(value = "json")String json){
        userAdd m = JSON.parseObject(json,userAdd.class);
        return userService.createUser(m);
    }


    /**
     * 构建userAdd模型删除账户
     * @param json
     * @return
     */
    @RequestMapping(value = "/both/{json}",method = RequestMethod.DELETE)
    public ReturnObject<Object> deleteUser(@PathVariable("json")String json){
        userAdd m = JSON.parseObject(json,userAdd.class);
        return userService.deleteUser(m);
    }
}
