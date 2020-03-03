package com.aixl.m.model;

import java.util.ArrayList;
import java.util.Date;

public class aiReport {

    private aiTestResult testResult;

    private ArrayList<aiTestResult> testResults;

    private ArrayList<aiTestImg> testImgs;

    private aiTestImg testImg;

    private aiTestHtml testHtml;

    private ArrayList<aiTestHtml> testHtmls;

    public aiTestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(aiTestResult testResult) {
        this.testResult = testResult;
    }

    public ArrayList<aiTestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(ArrayList<aiTestResult> testResults) {
        this.testResults = testResults;
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

    public aiTestImg getTestImg() {
        return testImg;
    }

    public void setTestImg(aiTestImg testImg) {
        this.testImg = testImg;
    }

    @Override
    public String toString() {
        return "aiReport{" +
                ", testResult=" + testResult +
                ", testResults=" + testResults +
                ", testImgs=" + testImgs +
                ", testImg=" + testImg +
                ", testHtml=" + testHtml +
                ", testHtmls=" + testHtmls +
                '}';
    }
}
