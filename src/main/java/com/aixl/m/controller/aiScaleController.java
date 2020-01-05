package com.aixl.m.controller;

import com.aixl.m.service.aiScaleService;
import com.aixl.m.utils.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/scales")
@CrossOrigin(origins = "*" , allowCredentials = "true")
public class aiScaleController {

    @Autowired
    aiScaleService scaleService;


    /**
     * 获取所有量基本信息表接口
     * @return
     */
    @RequestMapping(value = "/all" ,method = RequestMethod.GET)
    public ReturnObject<Object> getAll(){
        return scaleService.getAll();
    }



    /**
     *
     * 根据id获取量表题目信息接口
     * @param id
     * @return
     */
    @RequestMapping(value = "/testMsg/{id}",method = RequestMethod.GET)
    public ReturnObject<Object> getScaleMsg(@PathVariable("id") Integer id){
        return scaleService.getScaleTest(id);
    }
    
}
