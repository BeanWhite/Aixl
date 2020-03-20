package com.aixl.m.model;


import java.util.ArrayList;

/**
 * 数据包模型
 */
public class httpHeaderDataPackage {
    //目标群id
    private ArrayList<String> targets;

    //单个目标id
    private String target;

    //数据包名称
    private String dataName;

    //发送方id
    private String from_who;

    //数据状态
    private int status;

    //数据值
    private Object data;

    //其它数据
    private Object ot;

    //数据类型
    private String dataType;

    //数据分组
    private String group;

    //是否切割
    private boolean incise;

    //切割是否结束
    private boolean endIncise;

    //是否为长数据
    private boolean isLongData;

    //是否为主要数据
    private boolean _isMain;

    //数据操作
    private String dataOptions;

    //是否需要反馈消息
    private boolean isFeedBack;

    //反馈消息内容
    private String feedBack;

    //数据包是否需要缓存到服务器消息队列
    private boolean isCache;


    public Object getOt() {
        return ot;
    }

    public void setOt(Object ot) {
        this.ot = ot;
    }

    public boolean isCache() {
        return isCache;
    }

    public void setCache(boolean cache) {
        isCache = cache;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public boolean isEndIncise() {
        return endIncise;
    }

    public void setEndIncise(boolean endIncise) {
        this.endIncise = endIncise;
    }

    public boolean isFeedBack() {
        return isFeedBack;
    }

    public void setFeedBack(boolean feedBack) {
        isFeedBack = feedBack;
    }

    public String getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack;
    }

    public String getDataOptions() {
        return dataOptions;
    }

    public void setDataOptions(String dataOptions) {
        this.dataOptions = dataOptions;
    }

    public boolean is_isMain() {
        return _isMain;
    }

    public ArrayList<String> getTargets() {

        return targets;
    }

    public void setTargets(ArrayList<String> targets) {
        this._isMain = true;
        this.targets = targets;
    }

    public String getTarget() {

        return target;
    }

    public void setTarget(String target) {
        this._isMain = true;
        this.target = target;
    }

    public boolean isIncise() {
        return incise;
    }

    public void setIncise(boolean incise) {
        this.incise = incise;
    }

    public boolean isLongData() {
        return isLongData;
    }

    public void setLongData(boolean longData) {
        isLongData = longData;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
