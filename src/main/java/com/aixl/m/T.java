package com.aixl.m;

import com.aixl.m.model.aiUser;
import com.aixl.m.utils.Prince.Prince;
import com.aixl.m.utils.RedisUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class T {

    public static void main(String[] args) throws Exception {
        String string = "fasdfasdfasdfas对方的说法那是的可来得及发色大姐你佛I酷大家佛I安圣诞节哦哦拍的就佛";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes());
        Prince prince = new Prince("C:\\Program Files (x86)\\Prince\\engine\\bin\\prince.exe");
        //添加js
        //prince.addScript("");
        //添加css
        //prince.addStyleSheet("D:\\AixlProject\\AiWeb\\css\\print.css");
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
