package com.aixl.m;

import org.apache.ibatis.annotations.CacheNamespace;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@EnableAsync
@SpringBootApplication
@MapperScan("com.aixl.m.dao")
//@ComponentScan(basePackages = "com.aixl.m.utils")
public class Main {

    public static void main(String[] args) {
//        try{
//            Main main = new Main();
//            main.MBG();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
       SpringApplication.run(Main.class,args);
    }



    /**
     * 自动创建JavaBean、mapper、interface,调用完后需要取消调用，否则会清楚之后的修改内容并重建
     * @throws Exception
     */
    public void MBG() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/resources/mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
