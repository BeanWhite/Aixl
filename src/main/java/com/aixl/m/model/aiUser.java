package com.aixl.m.model;

public class aiUser {
    private String aiUserId;

    private String aiUserPwd;

    private String aiUserType;

    public String getAiUserId() {
        return aiUserId;
    }

    public void setAiUserId(String aiUserId) {
        this.aiUserId = aiUserId == null ? null : aiUserId.trim();
    }

    public String getAiUserPwd() {
        return aiUserPwd;
    }

    public void setAiUserPwd(String aiUserPwd) {
        this.aiUserPwd = aiUserPwd == null ? null : aiUserPwd.trim();
    }

    public String getAiUserType() {
        return aiUserType;
    }

    public void setAiUserType(String aiUserType) {
        this.aiUserType = aiUserType == null ? null : aiUserType.trim();
    }
}