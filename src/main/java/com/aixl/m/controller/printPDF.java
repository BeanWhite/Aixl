package com.aixl.m.controller;

import com.aixl.m.model.ImgModel;
import com.aixl.m.model.PicData;
import com.aixl.m.utils.Prince.Prince;
import com.aixl.m.utils.RedisUtils;
import com.aixl.m.utils.ReturnObject;
import com.aixl.m.utils.ReturnUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.pdf.codec.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sun.misc.BASE64Decoder;


import java.io.*;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class printPDF {

    // 图片路劲层级分隔符
    private static String separator = "/";

    @Autowired
    private RedisUtils redisUtils;


    /**
     * 打印报告
     * @param s html+css代码
     * @param name  pdf名称
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/p", method = RequestMethod.POST)
    public ReturnObject print(String s, String name) throws Exception {
        //System.out.println(s);
        //name = name.replaceAll(" ","");
        String uuid = UUID.randomUUID().toString().replaceAll("-","")+".pdf";
        Prince prince = new Prince("C:\\Program Files (x86)\\Prince\\engine\\bin\\prince.exe");
        //添加js
        //prince.addScript("");
        //添加css
        //prince.addStyleSheet("D:\\AixlProject\\AiWeb\\css\\print.css");
        //prince.addStyleSheet("C:\\Users\\h\\Desktop\\css.css");
        //添加html
        // prince.setBaseURL("D:\\AixlProject\\AiWeb\\new_file.html");

        //boolean b = prince.convertString(s, "D:\\AixlProject\\AiWeb\\js\\pdfDocument\\" + name);
        //服务器使用地址
        boolean b = prince.convertString(s,"D:\\nginx-1.17.7\\html\\xlrzpc\\js\\pdfDocument\\"+uuid);
        System.out.println(b+" "+uuid);
        //输入类型
        // prince.setInputType("html");
        //pdf输出
        //  prince.setPDFOutputIntent("D:\\");
        // prince.convert("D:\\AixlProject\\AiWeb\\new_file.html","D:\\1.pdf");
        //prince.convert("D:\\AixlProject\\AiWeb\\new_file.html",);
        return ReturnUtils.success(uuid,b);

    }

    @RequestMapping(value = "/p1", method = RequestMethod.POST)
    public Integer p1() {
//        MultipartHttpServletRequest mul = (MultipartHttpServletRequest)b;
//        MultipartFile file = mul.getFile("file");
        return 1111;
    }

    //获取待打印的图片位置信息
    @RequestMapping(value = "/picMsg", method = RequestMethod.GET)
    public ReturnObject getPicMsg(String scaleName) {
        try {
            return ReturnUtils.success(redisUtils.getCache(scaleName));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将pdf中的图片保存至服务器
     * @param imgModel  接收前端传过来的数据模型
     */
    @RequestMapping(value = "/Img", method = RequestMethod.POST)
    public void saveImg(@RequestBody JSONObject imgModel) {
        ImgModel img = JSON.parseObject(String.valueOf(imgModel), ImgModel.class);
        String rgex = "data:image/(.*?);base64"; //base64前缀

        ArrayList<PicData> images = img.getImages();
        for (int i = 0; i < images.size(); i++) {
            String baseImg = ""; //图片base64
            String fileName = "";//图片保存文件名，必须填
            String path = ""; //生基本成路径，可以不写

            baseImg = images.get(i).getUrl();
            fileName = images.get(i).getName();
            if (images.get(i).getPath().length() > 0&&images.get(i).getPath()!=null) {
                path = images.get(i).getPath();
            }
            //String type = "jpeg";
             String type = getSubUtilSimple(baseImg, rgex);//获取图片格式
            baseImg = baseImg.replaceFirst("data:(.+?);base64,", ""); //去除base64图片的前缀
            byte[] b;
            byte[] bs;
            OutputStream os = null;
            b = Base64.decode(baseImg.replaceAll(" ", "+")); //把图片转换成二进制
            fileName = fileName + "." + type;
            File file = new File(path);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            File imageFile = new File(path + "/" + fileName);
            BASE64Decoder d = new BASE64Decoder();
            // 保存
            try {
                //bs = d.decodeBuffer(Base64.encode(b));
                bs = d.decodeBuffer(Base64.encodeBytes(b));
                os = new FileOutputStream(imageFile);
                os.write(bs);
                //清空url节省空间、流量
                images.get(i).setUrl("");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.getLocalizedMessage();
                    }
                }
            }
        }
        //存入redius方便下次使用
       // redisUtils.setCache(images.get(0).getScaleName(), images);
    }
    public static String getSubUtilSimple(String soap, String rgex) {
        Pattern pattern = Pattern.compile(rgex);
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            return m.group(1);
        }
        return "";
    }
}
