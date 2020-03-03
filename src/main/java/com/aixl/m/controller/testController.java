package com.aixl.m.controller;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.data.annotation.AccessType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/t")
@CrossOrigin(origins = "*", allowCredentials = "true")
public class testController {

    //@Async
    @RequestMapping(value = "/t", method = RequestMethod.POST)
    public void test(@RequestBody String s) {
        System.out.println(s);
        //System.out.println(s);
        //test();
    }

    @RequestMapping(value = "/t/{s}",method = RequestMethod.PUT)
    public void t(@PathVariable(value = "s") String s){
        System.out.println(s);
    }
    @RequestMapping(value = "/t2", method = RequestMethod.POST)
    public void test2( String s) {
        System.out.println(s);
        //System.out.println(s);
        //test();
    }
    @RequestMapping(value = "/t1/{s}", method = RequestMethod.POST)
    public void test1(@PathVariable(value = "s")String s) {
        ActiveXComponent ax = null;

       // String str = "请A001号到3号窗口";
        String str =s;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            //运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume",
                    new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(0));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));

            //下面是构建文件流把生成语音文件

            ax = new ActiveXComponent("Sapi.SpFileStream");
            Dispatch spFileStream = ax.getObject();

            ax = new ActiveXComponent("Sapi.SpAudioFormat");
            Dispatch spAudioFormat = ax.getObject();

            //设置音频流格式
            Dispatch.put(spAudioFormat, "Type", new Variant(22));
            //设置文件输出流格式
            Dispatch.putRef(spFileStream, "Format", spAudioFormat);
            //调用输出 文件流打开方法，创建一个.wav文件
            //保存文件方式
            // Dispatch.call(spFileStream, "Open", new Variant("D:\\test.wav"), new Variant(3), new Variant(true));
//不保存文件方式
            Dispatch.call(spFileStream, "Open", new Variant(3), new Variant(true));

            //设置声音对象的音频输出流为输出文件对象
            Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
            //设置音量 0到100
            Dispatch.put(spVoice, "Volume", new Variant(100));
            //设置朗读速度
            Dispatch.put(spVoice, "Rate", new Variant(-2));
            //开始朗读
            Dispatch.call(spVoice, "Speak", new Variant(str));

            //关闭输出文件
            Dispatch.call(spFileStream, "Close");
            Dispatch.putRef(spVoice, "AudioOutputStream", null);

            spAudioFormat.safeRelease();
            spFileStream.safeRelease();
            spVoice.safeRelease();
            ax.safeRelease();

        } catch (Exception e) {
        }
    }
}
