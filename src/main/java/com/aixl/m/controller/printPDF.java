package com.aixl.m.controller;


import com.aixl.m.utils.Prince.Prince;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.websocket.server.PathParam;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;


@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "*" , allowCredentials = "true")
public class printPDF {


    @RequestMapping(value = "/p", method = RequestMethod.POST)
    public ReturnObject print(String s, String name) throws Exception{
       //System.out.println(s);
        Prince prince = new Prince("C:\\Program Files (x86)\\Prince\\engine\\bin\\prince.exe");
        //添加js
        //prince.addScript("");
        //添加css
        //prince.addStyleSheet("D:\\AixlProject\\AiWeb\\css\\print.css");
        //prince.addStyleSheet("C:\\Users\\h\\Desktop\\css.css");
        //添加html
        // prince.setBaseURL("D:\\AixlProject\\AiWeb\\new_file.html");

        prince.setBaseURL("D:\\AixlProject\\AiWeb\\html\\report\\report.html");
       // boolean b =   prince.convertString(s,"src/pdf/2.pdf");
        boolean b = prince.convertString(s,"D:\\AixlProject\\AiWeb\\js\\pdf.js\\"+name);
        System.out.println(b);
        //输入类型
        // prince.setInputType("html");


        //pdf输出
        //  prince.setPDFOutputIntent("D:\\");


       // prince.convert("D:\\AixlProject\\AiWeb\\new_file.html","D:\\1.pdf");

        //prince.convert("D:\\AixlProject\\AiWeb\\new_file.html",);
        return ReturnUtils.success(b);

    }

    @RequestMapping(value = "/p1",method = RequestMethod.POST)
    public Integer p1(){
//        MultipartHttpServletRequest mul = (MultipartHttpServletRequest)b;
//        MultipartFile file = mul.getFile("file");


       return 1111;
    }
}
