package com.aixl.m.controller;


import com.aixl.m.service.UserMsgService;
import com.aixl.m.utils.ReturnObject;
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


    @RequestMapping(value = "/msgForDoc/{str}",method = RequestMethod.GET)
    public ReturnObject<Object> getUserMsgForDoc(@PathVariable(value = "str") String string){
        System.out.println(string);
        return userMsgService.getUserMsgForDoc(string);
    }


}
