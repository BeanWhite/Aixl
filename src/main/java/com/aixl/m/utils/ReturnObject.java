package com.aixl.m.utils;

import com.alibaba.fastjson.JSON;

/**
 * 返回消息类
 * @param <T>  数据类
 */
public class ReturnObject <T extends Object > {
//    返回消息
    private String msg;
 //    返回数据对象
    private T object;

    //返回的附加数据包
    private Object o;

    //返回状态
    private String status;

    //返回数字状态
    private int status_n;

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public int getStatus_n() {
        return status_n;
    }

    public void setStatus_n(int status_n) {
        this.status_n = status_n;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
