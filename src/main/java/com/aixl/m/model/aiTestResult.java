package com.aixl.m.model;

import java.util.ArrayList;

public class aiTestResult {
    private String aiUserId;

    private Integer aiScaleId;

    private String aiTestName;

    private aiTestImg testImg;

    private ArrayList<aiTestImg> testImgs;

    private aiTestHtml testHtml;

    private ArrayList<aiTestHtml> testHtmls;


    public aiTestImg getTestImg() {
        return testImg;
    }

    public void setTestImg(aiTestImg testImg) {
        this.testImg = testImg;
    }

    public ArrayList<aiTestImg> getTestImgs() {
        return testImgs;
    }

    public void setTestImgs(ArrayList<aiTestImg> testImgs) {
        this.testImgs = testImgs;
    }

    public aiTestHtml getTestHtml() {
        return testHtml;
    }

    public void setTestHtml(aiTestHtml testHtml) {
        this.testHtml = testHtml;
    }

    public ArrayList<aiTestHtml> getTestHtmls() {
        return testHtmls;
    }

    public void setTestHtmls(ArrayList<aiTestHtml> testHtmls) {
        this.testHtmls = testHtmls;
    }


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

    public String getAiTestName() {
        return aiTestName;
    }

    public void setAiTestName(String aiTestName) {
        this.aiTestName = aiTestName == null ? null : aiTestName.trim();
    }

    @Override
    public String toString() {
        return "aiTestResult{" +
                "aiUserId='" + aiUserId + '\'' +
                ", aiScaleId=" + aiScaleId +
                ", aiTestName='" + aiTestName + '\'' +
                ", testImg=" + testImg +
                ", testImgs=" + testImgs +
                ", testHtml=" + testHtml +
                ", testHtmls=" + testHtmls +
                '}';
    }
}