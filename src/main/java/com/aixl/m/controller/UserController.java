package com.aixl.m.controller;


import com.aixl.m.model.aiDoc;
import com.aixl.m.model.aiUser;
import com.aixl.m.model.aiUserMsg;
import com.aixl.m.model.userAdd;
import com.aixl.m.service.UserMsgService;
import com.aixl.m.service.UserService;
import com.aixl.m.service.aiDocService;
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

    @Autowired
    private aiDocService docService;

    /**
     *  分页获取所有病人信息
     * @param id 从登录选项中获取的uuid值
     * @return  返回结果
     */
    @RequestMapping(value = "/{uuid}/msgsForDoc/{currentPage}/{pageSize}",method = RequestMethod.GET)
    public ReturnObject<Object> getUserMsgsForDoc(@PathVariable(value = "uuid")String id,
                                                  @PathVariable(value = "currentPage") Integer currentPage,
                                                  @PathVariable(value = "pageSize") Integer pageSize){
        if(id==null){

            //判断该id有没有被登录，如果没有登录直接返回，如果登陆判断是否合法，不合法同样直接返回
            return ReturnUtils.success("空内容");
        }else  if(id.equals("uuid")){
                return  userMsgService.getUserMsgsForDoc(currentPage,pageSize);
        }else {
            return  ReturnUtils.success("空帐号");
        }

    }

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
        aiUserMsg t = JSON.parseObject(string,aiUserMsg.class);
        return userMsgService.updateMsgForDoc(t);
    }

    @RequestMapping(value = "/Msg",method = RequestMethod.PUT)
    public ReturnObject<Object> setUserMsgForDoc1(String value){
        if(value==null){
            return  ReturnUtils.error("no content！");
        }
        aiUserMsg t = JSON.parseObject(value,aiUserMsg.class);
        return userMsgService.updateMsgForDoc(t);
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
    public ReturnObject<Object> usesr(@PathVariable(value = "json") String string,@PathVariable(value = "v") Integer v,String doc){
        aiUser user = new aiUser();
        aiUserMsg userMsg = JSON.parseObject(string,aiUserMsg.class);
        if(v == 1){//修改信息
            //System.out.println("修改信息");
           return userMsgService.updateMsgForDoc(userMsg);
        }else if(v==0){ //新增信息
            user.setAiUserPwd("123456");
            user.setAiUserId(userMsg.getAiUserId());
            user.setAiUserType("用户");
            user.setAiUserSuperiorId(doc);
            userService.addUser(user);
            //System.out.println("新增信息");
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
        user.setAiUserStatus("正常");
        return userService.addUser(user);
    }

    /**
     * 医生账号创建病人账号和医生账号
     * @param type  未定义
     * @param value 创建的信息内容
     * @return
     */
    @RequestMapping(value = "/user/{type}",method = RequestMethod.POST)
    public ReturnObject<Object> addUserWhole(@PathVariable(value = "type") String type,
                                             String value){

        userAdd u = JSON.parseObject(value,userAdd.class);
        System.out.println(u.toString());
        if(u.getUserType().equals("医生")){
            //创建医生账户
            aiDoc doc = new aiDoc();

            doc.setAiDocId(u.getId());
            doc.setAiDocPwd("123456");
            doc.setAiDocName(u.getName());
            doc.setAiDocSex(u.getSex());
            doc.setAiDocAge(u.getAge());
            doc.setAiDocOffice(u.getOffice());
            doc.setAiDocJob(u.getJob());
            doc.setAiDocEdu(u.getEdu());
            doc.setAiDocMarriage(u.getMarriage());
            doc.setAiDocFrom(u.getFrom());
            doc.setAiDocSuperiorId(u.getMasterId());
            return docService.addDoc(doc);
        }else if(u.getUserType().equals("用户")){
            //创建病人
            aiUser user = new aiUser();
            aiUserMsg userMsg = new aiUserMsg();

            user.setAiUserId(u.getId());
            user.setAiUserStatus("正常");
            user.setAiUserPwd("123456");
            user.setAiUserName(u.getName());
            user.setAiUserSuperiorId(u.getMasterId());

            userMsg.setAiUserId(u.getId());
            userMsg.setAiDocId(u.getMasterId());
            userMsg.setAiUserName(u.getName());
            userMsg.setAiUserSex(u.getSex());
            userMsg.setAiUserAge(u.getAge());
            userMsg.setAiUserOffice(u.getOffice());
            userMsg.setAiUserProfession(u.getJob());
            userMsg.setAiUserEduBg(u.getEdu());
            userMsg.setAiUserMarriage(u.getMarriage());
            userMsg.setAiUserFrom(u.getFrom());
            return userService.addUserWhole(user,userMsg);
        }else
            return ReturnUtils.success("错误账户类型："+u.getUserType());
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


    /**
     * 分页获取用户状态
     * @param a     当前页码
     * @param b     页面大小
     * @return
     */
    @RequestMapping(value = "/status/{id}/{currentPage}/{pageSize}",method = RequestMethod.GET)
    public ReturnObject<Object> getUserStatus(@PathVariable(value = "id") String id,@PathVariable(value = "currentPage")Integer a,
                                              @PathVariable(value = "pageSize")Integer b){
        return userService.getUserStatus(a,b,id);
    }

    /**
     * 根据用户id搜索用户状态
     * @param key   查询关键字
     * @param docId 发起查询的医生账号
     * @return
     */
    @RequestMapping(value = "/status/{key}/search/{docId}/{currentPage}/{pageSize}",method = RequestMethod.GET)
    public ReturnObject<Object> getUserStatusById(@PathVariable(value = "key") String key,
                                                  @PathVariable(value = "docId")String docId,
                                                  @PathVariable(value = "currentPage")Integer currentPage,
                                                  @PathVariable(value = "pageSize")Integer pageSize){

        return userService.getUserStatusById(key,docId,currentPage,pageSize);
    }

    /**
     * 根据用户id设置用户状态
     * @param id    用户id
     * @param status    接受状态，0表示作废当前账号，1表示重启当前账号
     * @return
     */
    @RequestMapping(value = "/status/{id}/{status}",method = RequestMethod.PUT)
    public ReturnObject<Object> setUserStatusById(@PathVariable(value = "id")String id,
                                                  @PathVariable(value = "status")String status){
        if(status.equals("0")){
            status = "作废";
        }else if(status.equals("1")){
            status = "正常";
        }else return ReturnUtils.success("状态异常");
        return userService.setUserStatusById(id,status);
    }
}
