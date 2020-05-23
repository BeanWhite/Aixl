package com.aixl.m.model;

public class aiUser {
    private String aiUserId;

    private String aiUserPwd;

    private String aiUserType="用户";

    private String aiUserStatus;

    private String aiUserName;

    public String getAiUserName() {
        return aiUserName;
    }

    public void setAiUserName(String aiUserName) {
        this.aiUserName = aiUserName;
    }

    public String getAiUserStatus() {
        return aiUserStatus;
    }

    public void setAiUserStatus(String aiUserStatus) {
        this.aiUserStatus = aiUserStatus;
    }

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

    @Override
    public String toString() {
        return "aiUser{" +
                "aiUserId='" + aiUserId + '\'' +
                ", aiUserPwd='" + aiUserPwd + '\'' +
                ", aiUserType='" + aiUserType + '\'' +
                '}';
    }
}