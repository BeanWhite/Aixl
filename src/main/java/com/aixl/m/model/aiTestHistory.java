package com.aixl.m.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class aiTestHistory {
    private String aiUserId;

    private String aiUserName;

    private Integer aiScaleId;

    private Date aiTestTime;

    private String aiScaleName;

    private String aiDocId;

    private String aiDocName;

    public String getAiUserId() {
        return aiUserId;
    }

    public void setAiUserId(String aiUserId) {
        this.aiUserId = aiUserId == null ? null : aiUserId.trim();
    }

    public Integer getAiScaleId() {
        return aiScaleId;
    }

    public void setAiScaleId(Integer aiScaleId) {
        this.aiScaleId = aiScaleId;
    }

    public String getAiTestTime() {
        if(aiTestTime==null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(aiTestTime);
        return dateStr;
    }

    public String getAiUserName() {
        return aiUserName;
    }

    public void setAiUserName(String aiUserName) {
        this.aiUserName = aiUserName;
    }

    public void setAiTestTime(Date aiTestTime) {
        this.aiTestTime = aiTestTime;
    }

    public String getAiScaleName() {
        return aiScaleName;
    }

    public void setAiScaleName(String aiScaleName) {
        this.aiScaleName = aiScaleName == null ? null : aiScaleName.trim();
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

    @Override
    public String toString() {
        return "aiTestHistory{" +
                "aiUserId='" + aiUserId + '\'' +
                ", aiUserName='" + aiUserName + '\'' +
                ", aiScaleId=" + aiScaleId +
                ", aiTestTime=" + aiTestTime +
                ", aiScaleName='" + aiScaleName + '\'' +
                ", aiDocId='" + aiDocId + '\'' +
                ", aiDocName='" + aiDocName + '\'' +
                '}';
    }
}