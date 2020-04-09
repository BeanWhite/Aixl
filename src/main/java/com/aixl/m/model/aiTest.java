package com.aixl.m.model;

import java.util.Arrays;

public class aiTest {
    private Integer aiScaleId;

    private Integer aiQuestionId;

    private String aiQuestionContent;

    //对上一个变量进行分解处理后的结果
    private String[] aiQuestionContents;

    private String aiQuestionOption;
    private String[] aiQuestionOptions;

    private String aiQuestionAnswer;
    private String[] aiQuestionAnswers;

    private String aiQuestionType;
    private String[] aiQuestionTypes;

    private String aiQuestionScore;
    private String[] aiQuestionScores;

    private String aiQuestionNotices[];
    private String aiQuestionNotice;

    private String aiQuestionGuids[];
    private String aiQuestionGuid;

    private String aiQuestionAnswerDescribes[];
    private String aiQuestionAnswerDescribe;

    private String[] aiScoreMethodNames;
    private String aiScoreMethodName;

    private String aiScoreMethods[];
    private String aiScoreMethod;

    private byte[] aiQuestionImg;

    private String aiScaleName;
    private String[] aiScaleNames;

    public String[] getAiQuestionContents() {
        return aiQuestionContents;
    }

    public void setAiQuestionContents(String[] aiQuestionContents) {
        this.aiQuestionContents = aiQuestionContents;
    }

    public String[] getAiQuestionOptions() {
        return aiQuestionOptions;
    }

    public void setAiQuestionOptions(String[] aiQuestionOptions) {
        this.aiQuestionOptions = aiQuestionOptions;
    }

    public String[] getAiQuestionAnswers() {
        return aiQuestionAnswers;
    }

    public void setAiQuestionAnswers(String[] aiQuestionAnswers) {
        this.aiQuestionAnswers = aiQuestionAnswers;
    }

    public String[] getAiQuestionTypes() {
        return aiQuestionTypes;
    }

    public void setAiQuestionTypes(String[] aiQuestionTypes) {
        this.aiQuestionTypes = aiQuestionTypes;
    }

    public String[] getAiQuestionScores() {
        return aiQuestionScores;
    }

    public void setAiQuestionScores(String[] aiQuestionScores) {
        this.aiQuestionScores = aiQuestionScores;
    }

    public String[] getAiQuestionNotices() {
        return aiQuestionNotices;
    }

    public void setAiQuestionNotices(String[] aiQuestionNotices) {
        this.aiQuestionNotices = aiQuestionNotices;
    }

    public String[] getAiQuestionGuids() {
        return aiQuestionGuids;
    }

    public void setAiQuestionGuids(String[] aiQuestionGuids) {
        this.aiQuestionGuids = aiQuestionGuids;
    }

    public String[] getAiQuestionAnswerDescribes() {
        return aiQuestionAnswerDescribes;
    }

    public void setAiQuestionAnswerDescribes(String[] aiQuestionAnswerDescribes) {
        this.aiQuestionAnswerDescribes = aiQuestionAnswerDescribes;
    }

    public String[] getAiScoreMethodNames() {
        return aiScoreMethodNames;
    }

    public void setAiScoreMethodNames(String[] aiScoreMethodNames) {
        this.aiScoreMethodNames = aiScoreMethodNames;
    }

    public String[] getAiScoreMethods() {
        return aiScoreMethods;
    }

    public void setAiScoreMethods(String[] aiScoreMethods) {
        this.aiScoreMethods = aiScoreMethods;
    }

    public String[] getAiScaleNames() {
        return aiScaleNames;
    }

    public void setAiScaleNames(String[] aiScaleNames) {
        this.aiScaleNames = aiScaleNames;
    }

    public String getAiScaleName() {
        return aiScaleName;
    }

    public void setAiScaleName(String aiScaleName) {
        this.aiScaleName = aiScaleName;
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

    @Override
    public String toString() {
        return "aiTest{" +
                "aiScaleId=" + aiScaleId +
                ", aiQuestionId=" + aiQuestionId +
                ", aiQuestionContent='" + aiQuestionContent + '\'' +
                ", aiQuestionContents=" + Arrays.toString(aiQuestionContents) +
                ", aiQuestionOption='" + aiQuestionOption + '\'' +
                ", aiQuestionOptions=" + Arrays.toString(aiQuestionOptions) +
                ", aiQuestionAnswer='" + aiQuestionAnswer + '\'' +
                ", aiQuestionAnswers=" + Arrays.toString(aiQuestionAnswers) +
                ", aiQuestionType='" + aiQuestionType + '\'' +
                ", aiQuestionTypes=" + Arrays.toString(aiQuestionTypes) +
                ", aiQuestionScore='" + aiQuestionScore + '\'' +
                ", aiQuestionScores=" + Arrays.toString(aiQuestionScores) +
                ", aiQuestionNotices=" + Arrays.toString(aiQuestionNotices) +
                ", aiQuestionNotice='" + aiQuestionNotice + '\'' +
                ", aiQuestionGuids=" + Arrays.toString(aiQuestionGuids) +
                ", aiQuestionGuid='" + aiQuestionGuid + '\'' +
                ", aiQuestionAnswerDescribes=" + Arrays.toString(aiQuestionAnswerDescribes) +
                ", aiQuestionAnswerDescribe='" + aiQuestionAnswerDescribe + '\'' +
                ", aiScoreMethodNames=" + Arrays.toString(aiScoreMethodNames) +
                ", aiScoreMethodName='" + aiScoreMethodName + '\'' +
                ", aiScoreMethods=" + Arrays.toString(aiScoreMethods) +
                ", aiScoreMethod='" + aiScoreMethod + '\'' +
                ", aiQuestionImg=" + Arrays.toString(aiQuestionImg) +
                ", aiScaleName='" + aiScaleName + '\'' +
                ", aiScaleNames=" + Arrays.toString(aiScaleNames) +
                '}';
    }
}