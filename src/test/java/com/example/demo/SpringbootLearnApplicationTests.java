package com.example.demo;


import com.example.demo.service.AsyncTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringbootLearnApplicationTests {

    @Autowired
    private AsyncTaskService asyncTaskService;


    @Test
    public void contextLoads(){

    }

    @Test
    public void threadTest(){
        for (int i=0;i<20;i++)
            asyncTaskService.executeAsyncTask(i);
            //System.out.println("线程"+Thread.currentThread().getName()+"执行异步任务"+i);
    }
}
