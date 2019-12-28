package com.aixl.m.model;

public class aiScale {
    private Integer aiScaleId;

    private String aiScaleName;

    private String aiScaleOverview;

    private String aiScaleNotice;

    public Integer getAiScaleId() {
        return aiScaleId;
    }

    public void setAiScaleId(Integer aiScaleId) {
        this.aiScaleId = aiScaleId;
    }

    public String getAiScaleName() {
        return aiScaleName;
    }

    public void setAiScaleName(String aiScaleName) {
        this.aiScaleName = aiScaleName == null ? null : aiScaleName.trim();
    }

    public String getAiScaleOverview() {
        return aiScaleOverview;
    }

    public void setAiScaleOverview(String aiScaleOverview) {
        this.aiScaleOverview = aiScaleOverview == null ? null : aiScaleOverview.trim();
    }

    public String getAiScaleNotice() {
        return aiScaleNotice;
    }

    public void setAiScaleNotice(String aiScaleNotice) {
        this.aiScaleNotice = aiScaleNotice == null ? null : aiScaleNotice.trim();
    }
}