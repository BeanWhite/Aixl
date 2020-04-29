package com.aixl.m.controller;



import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/robot")
public class robot {

    @RequestMapping(value = "/getDoc",method = RequestMethod.POST)
    public String startRobot(String ul) throws IOException {
        if(ul==null)
            return  "null";
        URL url = null;
        URLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        String regex = "https://[\\w+\\.?/?]+\\.[A-Za-z]+";
        Pattern pattern = Pattern.compile(regex);
        try {
            url = new URL(ul);//爬取的网站
            urlConnection = url.openConnection();
            printWriter = new PrintWriter(new FileWriter("D:/siteURL.doc"),true);
            bufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()
            ));
            String buf = null;
            while ((buf = bufferedReader.readLine())!=null){
                Matcher buf_m = pattern.matcher(buf);
                while (buf_m.find()){
                    printWriter.println(buf_m.group());
                }
            }
            System.out.println("爬取成功");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            printWriter.close();
        }
        return "success";
    }

    String url = "http://www.baidu.com";//待爬取的URL
    //获取url页面的源码；
    public String ClimbBug() throws IOException{
        URL accessurl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) accessurl.openConnection();
        conn.connect();//连接
        InputStream is = null;



        if(conn.getResponseCode()==200){//判断状态吗是否为200，正常的话获取返回信息
            byte[] b=new byte[4096];
            is = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            int len=0;
            while((len=is.read(b))!=-1){
                sb.append(new String(b,0,len,"UTF-8"));
            }
            return sb.toString();//返回百度页面源代码，这里跟网页上右键查看源代码的效果一致
        }
        return "Error";
    }
    //获取图片页面URL
    public List regex() throws IOException{

        String regex = "src[\\s\\S]*?>";//匹配所有图片的URL
        List<String> list = new ArrayList<String>();
        Matcher p = Pattern.compile(regex).matcher(new robot().ClimbBug());
        while(p.find()){
            list.add(p.group());
        }//匹配到放入我们的URLlist
        return list;
    }
    //下载图片到本地或者结合数据库做其他想做的操作;
    public void download(List list) throws IOException{
        InputStream is =null;
        OutputStream os =null;
        for (int i=0;i<list.size();i++){
            String url = list.get(i).toString();
            String tmp="";
            if(url.contains("jpg")){
                tmp = "http://www.baidu.com/img/"+url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("jpg"))+"jpg";
            }else if(url.contains("png")){
                tmp = "http://www.baidu.com/img/"+url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("png"))+"png";
                System.out.println(tmp);
            }else if(url.contains("gif")){
                tmp = "http://www.baidu.com/img/"+url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("gif"))+"gif";
            }//tmp为实际图片的URL
            is = new URL(tmp).openStream();//打开流
            String path = "D://sgx";
            System.out.println("path =="+path);
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }//建立文件夹
            File file2 = new File(path,tmp.substring(tmp.lastIndexOf('/')+1));
            if(!file2.exists()){
                file2.createNewFile();
            }//建立file对象
            os = new FileOutputStream(file2);
            byte[] b =new byte[4096];
            int len;
            while((len=is.read(b))!=-1){
                os.write(b,0,len);
            }
            os.flush();
        }
        is.close();
        os.close();
    }

//    public static void main(String[] args) throws IOException {
//        new Bug().download(new Bug().regex());
//    }



}
