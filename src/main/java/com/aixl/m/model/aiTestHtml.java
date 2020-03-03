package com.aixl.m.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class aiTestHtml {
    private Date aiReportTextTime;

    private String aiReportTextId;

    private String aiDocId;

    private String aiDocName;

    private Integer aiScaleId;

    private String aiReportTextHtml;

    public String getAiReportTextTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(aiReportTextTime);
        return dateStr;
    }

    public Date getTime(){
        return aiReportTextTime;
    }

    public void setAiReportTextTime(Date aiReportTextTime) {
        this.aiReportTextTime = aiReportTextTime;
    }

    public String getAiReportTextId() {
        return aiReportTextId;
    }

    public void setAiReportTextId(String aiReportTextId) {
        this.aiReportTextId = aiReportTextId == null ? null : aiReportTextId.trim();
    }

    public String getAiDocId() {
        return aiDocId;
    }

    public void setAiDocId(String aiDocId) {
        this.aiDocId = aiDocId == null ? null : aiDocId.trim();
    }

    public String getAiDocName() {
        return aiDocName;
    }

    public void setAiDocName(String aiDocName) {
        this.aiDocName = aiDocName == null ? null : aiDocName.trim();
    }

    public Integer getAiScaleId() {
        return aiScaleId;
    }

    public void setAiScaleId(Integer aiScaleId) {
        this.aiScaleId = aiScaleId;
    }

    public String getAiReportTextHtml() {
        return aiReportTextHtml;
    }

    public void setAiReportTextHtml(String aiReportTextHtml) {
        this.aiReportTextHtml = aiReportTextHtml == null ? null : aiReportTextHtml.trim();
    }

    @Override
    public String toString() {
        return "aiTestHtml{" +
                "aiReportTextTime=" + aiReportTextTime +
                ", aiReportTextId='" + aiReportTextId + '\'' +
                ", aiDocId='" + aiDocId + '\'' +
                ", aiDocName='" + aiDocName + '\'' +
                ", aiScaleId=" + aiScaleId +
                ", aiReportTextHtml='" + aiReportTextHtml + '\'' +
                '}';
    }
}