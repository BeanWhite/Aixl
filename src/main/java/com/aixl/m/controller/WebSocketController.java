package com.aixl.m.controller;

import com.aixl.m.service.WebSocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/sendInfo")
public class WebSocketController {
    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid){
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid",cid);
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public Object pushToWeb(@PathVariable String cid,String message){
        try {
            WebSocketServer.sendInfo(message,cid);
        }catch (Exception e){
            e.printStackTrace();
            return "出错";
        }
        return "没有出错";
    }

}
