package com.aixl.m.model;

import java.util.Arrays;

public class aiTest {
    private Integer aiScaleId;

    private Integer aiQuestionId;

    private String aiQuestionContent;

    //对上一个变量进行分解处理后的结果
    private Object aiQuestionContents;

    private String aiQuestionOption;
    private Object aiQuestionOptions;

    private String aiQuestionAnswer;
    private Object aiQuestionAnswers;

    private String aiQuestionType;
    private Object aiQuestionTypes;

    private String aiQuestionScore;
    private Object aiQuestionScores;

    private Object aiQuestionNotices;
    private String aiQuestionNotice;

    private Object aiQuestionGuids;
    private String aiQuestionGuid;

    private Object aiQuestionAnswerDescribes;
    private String aiQuestionAnswerDescribe;

    private Object aiScoreMethodNames;
    private String aiScoreMethodName;

    private Object aiScoreMethods;
    private String aiScoreMethod;

    private byte[] aiQuestionImg;

    private String aiScaleName;
    private Object aiScaleNames;

    private Exception e;



    public void setAiQuestionContent(String aiQuestionContent) {
        this.aiQuestionContent = aiQuestionContent == null ? null : aiQuestionContent.trim();
    }

    public String getAiQuestionOption() {
        return aiQuestionOption;
    }

    public void setAiQuestionOption(String aiQuestionOption) {
        this.aiQuestionOption = aiQuestionOption == null ? null : aiQuestionOption.trim();
    }

    public String getAiQuestionAnswer() {
        return aiQuestionAnswer;
    }

    public void setAiQuestionAnswer(String aiQuestionAnswer) {
        this.aiQuestionAnswer = aiQuestionAnswer == null ? null : aiQuestionAnswer.trim();
    }

    public String getAiQuestionType() {
        return aiQuestionType;
    }

    public void setAiQuestionType(String aiQuestionType) {
        this.aiQuestionType = aiQuestionType == null ? null : aiQuestionType.trim();
    }

    public String getAiQuestionScore() {
        return aiQuestionScore;
    }

    public void setAiQuestionScore(String aiQuestionScore) {
        this.aiQuestionScore = aiQuestionScore == null ? null : aiQuestionScore.trim();
    }

    public String getAiQuestionNotice() {
        return aiQuestionNotice;
    }

    public void setAiQuestionNotice(String aiQuestionNotice) {
        this.aiQuestionNotice = aiQuestionNotice == null ? null : aiQuestionNotice.trim();
    }

    public String getAiQuestionGuid() {
        return aiQuestionGuid;
    }

    public void setAiQuestionGuid(String aiQuestionGuid) {
        this.aiQuestionGuid = aiQuestionGuid == null ? null : aiQuestionGuid.trim();
    }

    public String getAiQuestionAnswerDescribe() {
        return aiQuestionAnswerDescribe;
    }

    public void setAiQuestionAnswerDescribe(String aiQuestionAnswerDescribe) {
        this.aiQuestionAnswerDescribe = aiQuestionAnswerDescribe == null ? null : aiQuestionAnswerDescribe.trim();
    }

    public String getAiScoreMethodName() {
        return aiScoreMethodName;
    }

    public void setAiScoreMethodName(String aiScoreMethodName) {
        this.aiScoreMethodName = aiScoreMethodName == null ? null : aiScoreMethodName.trim();
    }

    public String getAiScoreMethod() {
        return aiScoreMethod;
    }

    public void setAiScoreMethod(String aiScoreMethod) {
        this.aiScoreMethod = aiScoreMethod == null ? null : aiScoreMethod.trim();
    }

    public byte[] getAiQuestionImg() {
        return aiQuestionImg;
    }

    public void setAiQuestionImg(byte[] aiQuestionImg) {
        this.aiQuestionImg = aiQuestionImg;
    }

    public Integer getAiScaleId() {
        return aiScaleId;
    }

    public void setAiScaleId(Integer aiScaleId) {
        this.aiScaleId = aiScaleId;
    }

    public Integer getAiQuestionId() {
        return aiQuestionId;
    }

    public void setAiQuestionId(Integer aiQuestionId) {
        this.aiQuestionId = aiQuestionId;
    }

    public String getAiQuestionContent() {
        return aiQuestionContent;
    }

    public Object getAiQuestionContents() {
        return aiQuestionContents;
    }

    public void setAiQuestionContents(Object aiQuestionContents) {
        this.aiQuestionContents = aiQuestionContents;
    }

    public Object getAiQuestionOptions() {
        return aiQuestionOptions;
    }

    public void setAiQuestionOptions(Object aiQuestionOptions) {
        this.aiQuestionOptions = aiQuestionOptions;
    }

    public Object getAiQuestionAnswers() {
        return aiQuestionAnswers;
    }

    public void setAiQuestionAnswers(Object aiQuestionAnswers) {
        this.aiQuestionAnswers = aiQuestionAnswers;
    }

    public Object getAiQuestionTypes() {
        return aiQuestionTypes;
    }

    public void setAiQuestionTypes(Object aiQuestionTypes) {
        this.aiQuestionTypes = aiQuestionTypes;
    }

    public Object getAiQuestionScores() {
        return aiQuestionScores;
    }

    public void setAiQuestionScores(Object aiQuestionScores) {
        this.aiQuestionScores = aiQuestionScores;
    }

    public Object getAiQuestionNotices() {
        return aiQuestionNotices;
    }

    public void setAiQuestionNotices(Object aiQuestionNotices) {
        this.aiQuestionNotices = aiQuestionNotices;
    }

    public Object getAiQuestionGuids() {
        return aiQuestionGuids;
    }

    public void setAiQuestionGuids(Object aiQuestionGuids) {
        this.aiQuestionGuids = aiQuestionGuids;
    }

    public Object getAiQuestionAnswerDescribes() {
        return aiQuestionAnswerDescribes;
    }

    public void setAiQuestionAnswerDescribes(Object aiQuestionAnswerDescribes) {
        this.aiQuestionAnswerDescribes = aiQuestionAnswerDescribes;
    }

    public Object getAiScoreMethodNames() {
        return aiScoreMethodNames;
    }

    public void setAiScoreMethodNames(Object aiScoreMethodNames) {
        this.aiScoreMethodNames = aiScoreMethodNames;
    }

    public Object getAiScoreMethods() {
        return aiScoreMethods;
    }

    public void setAiScoreMethods(Object aiScoreMethods) {
        this.aiScoreMethods = aiScoreMethods;
    }

    public String getAiScaleName() {
        return aiScaleName;
    }

    public void setAiScaleName(String aiScaleName) {
        this.aiScaleName = aiScaleName;
    }

    public Object getAiScaleNames() {
        return aiScaleNames;
    }

    public void setAiScaleNames(Object aiScaleNames) {
        this.aiScaleNames = aiScaleNames;
    }

    @Override
    public String toString() {
        return "aiTest{" +
                "aiScaleId=" + aiScaleId +
                ", aiQuestionId=" + aiQuestionId +
                ", aiQuestionContent='" + aiQuestionContent + '\'' +
                ", aiQuestionContents=" + aiQuestionContents +
                ", aiQuestionOption='" + aiQuestionOption + '\'' +
                ", aiQuestionOptions=" + aiQuestionOptions +
                ", aiQuestionAnswer='" + aiQuestionAnswer + '\'' +
                ", aiQuestionAnswers=" + aiQuestionAnswers +
                ", aiQuestionType='" + aiQuestionType + '\'' +
                ", aiQuestionTypes=" + aiQuestionTypes +
                ", aiQuestionScore='" + aiQuestionScore + '\'' +
                ", aiQuestionScores=" + aiQuestionScores +
                ", aiQuestionNotices=" + aiQuestionNotices +
                ", aiQuestionNotice='" + aiQuestionNotice + '\'' +
                ", aiQuestionGuids=" + aiQuestionGuids +
                ", aiQuestionGuid='" + aiQuestionGuid + '\'' +
                ", aiQuestionAnswerDescribes=" + aiQuestionAnswerDescribes +
                ", aiQuestionAnswerDescribe='" + aiQuestionAnswerDescribe + '\'' +
                ", aiScoreMethodNames=" + aiScoreMethodNames +
                ", aiScoreMethodName='" + aiScoreMethodName + '\'' +
                ", aiScoreMethods=" + aiScoreMethods +
                ", aiScoreMethod='" + aiScoreMethod + '\'' +
                ", aiQuestionImg=" + Arrays.toString(aiQuestionImg) +
                ", aiScaleName='" + aiScaleName + '\'' +
                ", aiScaleNames=" + aiScaleNames +
                ", e=" + e +
                '}';
    }
}