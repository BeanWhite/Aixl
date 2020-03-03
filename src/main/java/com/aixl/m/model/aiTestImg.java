package com.aixl.m.model;

import java.util.Date;

public class aiTestImg {
    private String aiImgId;

    private Date aiReportTime;

    private String aiImgSrc;

    private Integer aiScaleId;

    private String aiImgContent;

    public String getAiImgId() {
        return aiImgId;
    }

    public void setAiImgId(String aiImgId) {
        this.aiImgId = aiImgId == null ? null : aiImgId.trim();
    }

    public Date getAiReportTime() {
        return aiReportTime;
    }

    public void setAiReportTime(Date aiReportTime) {
        this.aiReportTime = aiReportTime;
    }

    public String getAiImgSrc() {
        return aiImgSrc;
    }

    public void setAiImgSrc(String aiImgSrc) {
        this.aiImgSrc = aiImgSrc == null ? null : aiImgSrc.trim();
    }

    public Integer getAiScaleId() {
        return aiScaleId;
    }

    public void setAiScaleId(Integer aiScaleId) {
        this.aiScaleId = aiScaleId;
    }

    public String getAiImgContent() {
        return aiImgContent;
    }

    public void setAiImgContent(String aiImgContent) {
        this.aiImgContent = aiImgContent == null ? null : aiImgContent.trim();
    }

    @Override
    public String toString() {
        return "aiTestImg{" +
                "aiImgId='" + aiImgId + '\'' +
                ", aiReportTime=" + aiReportTime +
                ", aiImgSrc='" + aiImgSrc + '\'' +
                ", aiScaleId=" + aiScaleId +
                ", aiImgContent='" + aiImgContent + '\'' +
                '}';
    }
}