package com.aixl.m.utils;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 全局异常处理
 */
@ControllerAdvice
public class MyExceptionHander {
    public static final String ERROR_VIEW = "my_error";//指定异常路径
    @ExceptionHandler(value = Exception.class)  //指定拦截的异常
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response,Exception e) throws Exception{
        e.printStackTrace();
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception",e);
        mv.addObject("url",request.getRequestURI());    //发生异常路径
        mv.setViewName(ERROR_VIEW); //指定发生异常后的跳转页面
        return mv;
    }
}