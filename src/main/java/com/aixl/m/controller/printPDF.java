package com.aixl.m.controller;


import com.aixl.m.utils.Prince.Prince;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "*" , allowCredentials = "true")
public class printPDF {

    @RequestMapping(value = "/p", method = RequestMethod.POST)
    public void print(InputStream input) throws Exception{
        String string = "fasdfasdfasdfas对方的说法那是的可来得及发色大姐你佛I酷大家佛I安圣诞节哦哦拍的就佛";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        Prince prince = new Prince("C:\\Program Files (x86)\\Prince\\engine\\bin\\prince.exe");
        //添加js
        //prince.addScript("");
        //添加css
        prince.addStyleSheet("D:\\AixlProject\\AiWeb\\css\\print.css");

        //添加html
        // prince.setBaseURL("D:\\AixlProject\\AiWeb\\new_file.html");
        prince.setHTML(true);
        prince.setJavaScript(true);

        //输入类型
        // prince.setInputType("html");


        //pdf输出
        //  prince.setPDFOutputIntent("D:\\");
        prince.convert("D:\\AixlProject\\AiWeb\\new_file.html","D:\\1.pdf");

        //prince.convert("D:\\AixlProject\\AiWeb\\new_file.html",);

    }

}
