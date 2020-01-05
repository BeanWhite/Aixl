package com.aixl.m.model;


/**
 * 数据包模型
 */
public class httpHeaderDataPackage {
    //目标id
    private String target;

    //发送方id
    private String from_who;

    //数据状态
    private int status;

    //数据内容
    private Object data;

    //数据类型
    private String dataType;


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getFrom_who() {
        return from_who;
    }

    public void setFrom_who(String from_who) {
        this.from_who = from_who;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
