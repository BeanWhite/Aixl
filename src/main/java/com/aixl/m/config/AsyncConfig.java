package com.aixl.m.config;


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 多线程异步调用配置类
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    // ThredPoolTaskExcutor的处理流程
    // 当池子大小小于corePoolSize，就新建线程，并处理请求
    // 当池子大小等于corePoolSize，把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理
    // 当workQueue放不下任务时，就新建线程入池，并处理请求，如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理
    // 当池子的线程数大于corePoolSize时，多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        //创建线程池
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();

        //设置核心线程数
        threadPool.setCorePoolSize(1000);
        //设置最大线程数
        threadPool.setMaxPoolSize(50000);

        //线程池所用缓存队列
        threadPool.setQueueCapacity(100000);
        //等待任务在关机时完成 --表名等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(true);

        //等待时间（默认值0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(50);
        //线程名称前缀
        threadPool.setThreadNamePrefix("Derry-Async-");
        //初始化
        threadPool.initialize();

        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
