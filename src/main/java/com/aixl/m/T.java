package com.aixl.m;

import com.aixl.m.utils.Prince.Prince;

public class T {

    public static void main(String[] args) throws Exception {
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
       

    }
}
