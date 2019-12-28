package com.aixl.m.model;

import java.util.Date;

public class aiTestResult {
    private String aiUserId;

    private String aiResultName;

    private String aiResultDescribe;

    private String aiResultLev;

    private Date aiResultTime;

    private Date aiResultLastTime;

    private String aiResultTotal;

    public String getAiUserId() {
        return aiUserId;
    }

    public void setAiUserId(String aiUserId) {
        this.aiUserId = aiUserId == null ? null : aiUserId.trim();
    }

    public String getAiResultName() {
        return aiResultName;
    }

    public void setAiResultName(String aiResultName) {
        this.aiResultName = aiResultName == null ? null : aiResultName.trim();
    }

    public String getAiResultDescribe() {
        return aiResultDescribe;
    }

    public void setAiResultDescribe(String aiResultDescribe) {
        this.aiResultDescribe = aiResultDescribe == null ? null : aiResultDescribe.trim();
    }

    public String getAiResultLev() {
        return aiResultLev;
    }

    public void setAiResultLev(String aiResultLev) {
        this.aiResultLev = aiResultLev == null ? null : aiResultLev.trim();
    }

    public Date getAiResultTime() {
        return aiResultTime;
    }

    public void setAiResultTime(Date aiResultTime) {
        this.aiResultTime = aiResultTime;
    }

    public Date getAiResultLastTime() {
        return aiResultLastTime;
    }

    public void setAiResultLastTime(Date aiResultLastTime) {
        this.aiResultLastTime = aiResultLastTime;
    }

    public String getAiResultTotal() {
        return aiResultTotal;
    }

    public void setAiResultTotal(String aiResultTotal) {
        this.aiResultTotal = aiResultTotal == null ? null : aiResultTotal.trim();
    }
}