package com.aixl.m.controller;


import com.aixl.m.service.UserService;
import com.aixl.m.service.aiAdminService;

import com.aixl.m.service.aiDocService;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/login")
public class Login {

    @Autowired
    private aiAdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private aiDocService docService;


    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ReturnObject<Object> getAdmin(String id, String pwd) {
        return adminService.getAdmin(id,pwd);
    }

    @RequestMapping(value = "/doc",method = RequestMethod.POST)
    public ReturnObject<Object> getDoc(String id,String pwd){
        return docService.getDoc(id,pwd);
    }

    @RequestMapping(value = "user",method = RequestMethod.POST)
    public ReturnObject<Object> getUser(String id,String pwd){
        return userService.getUser(id,pwd);
    }

}
