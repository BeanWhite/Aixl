package com.aixl.m.model;

public class aiTest {
    private Integer aiScaleId;

    private Integer aiQuestionId;

    private String aiQuestionContent;

    private String aiQuestionOption;

    private String aiQuestionAnswer;

    private String aiQuestionType;

    private String aiQuestionScore;

    private String aiQuestionNotice;

    private String aiQuestionGuid;

    private String aiQuestionAnswerDescribe;

    private String aiScoreMethodName;

    private String aiScoreMethod;

    private byte[] aiQuestionImg;

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
}