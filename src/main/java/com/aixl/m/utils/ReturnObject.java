package com.aixl.m.utils;

import com.alibaba.fastjson.JSON;

/**
 * 返回消息类
 * @param <T>  数据类
 */
public class ReturnObject <T extends Object > {
//    返回状态消息
    private String msg;
 //    返回数据对象
    private T object;

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
