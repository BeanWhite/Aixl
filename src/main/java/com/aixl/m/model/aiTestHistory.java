package com.aixl.m.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class aiTestHistory {

    private List<aiTestHistory> groupByStartTime;

    private String aiUserId;

    private String aiUserName;

    private Integer aiScaleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date aiTestTime;

    private String aiScaleName;

    private String aiDocId;

    private String aiDocName;

    private String reportContent;

    private String msg;

    private Date startTime;

    public List<aiTestHistory> getGroupByStartTime() {
        return groupByStartTime;
    }


    public void setGroupByStartTime(List<aiTestHistory> groupByStartTime) {
        this.groupByStartTime = groupByStartTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReportContent() {

        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
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

    public String getAiUserName() {
        return aiUserName;
    }

    public void setAiUserName(String aiUserName) {
        this.aiUserName = aiUserName;
    }

    public Date getAiTestTime() {
        return aiTestTime;
    }

    public void setAiTestTime(Date aiTestTime) {
        this.aiTestTime = aiTestTime;
    }

    //    public String getTime() {
//        if(aiTestTime==null)
//            return null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = sdf.format(aiTestTime);
//        return dateStr;
//    }

//    public Date getAiTestTime() {
//        return aiTestTime;
//    }

    public void setTime(String aiTestTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateTime = null;
        try {
            dateTime = simpleDateFormat.parse(aiTestTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.aiTestTime = dateTime;
    }

//    public void setAiTestTime(Date d){
//        this.aiTestTime =d;
//    }

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
                "groupByStartTime=" + groupByStartTime +
                ", aiUserId='" + aiUserId + '\'' +
                ", aiUserName='" + aiUserName + '\'' +
                ", aiScaleId=" + aiScaleId +
                ", aiTestTime=" + aiTestTime +
                ", aiScaleName='" + aiScaleName + '\'' +
                ", aiDocId='" + aiDocId + '\'' +
                ", aiDocName='" + aiDocName + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", msg='" + msg + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}