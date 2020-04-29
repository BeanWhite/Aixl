package com.aixl.m.controller;


import com.aixl.m.model.aiUser;
import com.aixl.m.model.aiUserMsg;
import com.aixl.m.model.userAdd;
import com.aixl.m.service.UserMsgService;
import com.aixl.m.service.UserService;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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
        aiUserMsg t = JSON.parseObject(string,aiUserMsg.class);
        return userMsgService.addUser(t);
    }

    /**
     * 医生发布测评时修改或新建用户
     * @param string    用户信息类
     * @param v 1表示修改信息，0表示新增用户
     * @return
     */
    @RequestMapping(value = "/userW/{json}/{v}")
    public ReturnObject<Object> usesr(@PathVariable(value = "json") String string,@PathVariable(value = "v") Integer v){
        aiUser user = new aiUser();
        aiUserMsg userMsg = JSON.parseObject(string,aiUserMsg.class);
        if(v == 1){//修改信息
            System.out.println("修改信息");
           return userMsgService.updateMsgForDoc(string);
        }else if(v==0){ //新增信息
            user.setAiUserPwd("123456");
            user.setAiUserId(userMsg.getAiUserId());
            user.setAiUserType("用户");
          userService.addUser(user);
            System.out.println("新增信息");
          return userMsgService.addUser(userMsg);
        }
        return ReturnUtils.error("错误数据");
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
        aiUser user = JSON.parseObject(string, aiUser.class);
        return userService.addUser(user);
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
