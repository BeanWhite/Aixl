package com.example.demo.service;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(int i){
       Long L= System.currentTimeMillis();
       Long l;
       String s;
       while (true){
           l = System.currentTimeMillis();
           Date date = new Date(l);
           SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM + -dd hh:mm:ss");
           s=simpleDateFormat.format(date);
           if(l-L>10000)
               break;
       }
        Date date = new Date(L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM + -dd hh:mm:ss");
        System.out.println("线程"+Thread.currentThread().getName()+"执行异步任务"+i+"\t"+simpleDateFormat.format(date)+"\t"+s);
    }
}
