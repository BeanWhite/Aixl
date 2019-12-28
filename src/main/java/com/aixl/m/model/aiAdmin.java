package com.aixl.m.model;

public class aiAdmin {
    private String aiAdminId = null;

    private String aiAdminDepartment = null;

    private String aiAdminPwd = null;

    private String aiAdminUnit = null;

    private String aiAdminName = null;

    private Integer aiAdminLimit = null;

    private String aiUserType = null;

    private String aiIdCard = null;

    public String getAiAdminId() {
        return aiAdminId;
    }

    public void setAiAdminId(String aiAdminId) {
        this.aiAdminId = aiAdminId == null ? null : aiAdminId.trim();
    }

    public String getAiAdminDepartment() {
        return aiAdminDepartment;
    }

    public void setAiAdminDepartment(String aiAdminDepartment) {
        this.aiAdminDepartment = aiAdminDepartment == null ? null : aiAdminDepartment.trim();
    }

    public String getAiAdminPwd() {
        return aiAdminPwd;
    }

    public void setAiAdminPwd(String aiAdminPwd) {
        this.aiAdminPwd = aiAdminPwd == null ? null : aiAdminPwd.trim();
    }

    public String getAiAdminUnit() {
        return aiAdminUnit;
    }

    public void setAiAdminUnit(String aiAdminUnit) {
        this.aiAdminUnit = aiAdminUnit == null ? null : aiAdminUnit.trim();
    }

    public String getAiAdminName() {
        return aiAdminName;
    }

    public void setAiAdminName(String aiAdminName) {
        this.aiAdminName = aiAdminName == null ? null : aiAdminName.trim();
    }

    public Integer getAiAdminLimit() {
        return aiAdminLimit;
    }

    public void setAiAdminLimit(Integer aiAdminLimit) {
        this.aiAdminLimit = aiAdminLimit;
    }

    public String getAiUserType() {
        return aiUserType;
    }

    public void setAiUserType(String aiUserType) {
        this.aiUserType = aiUserType == null ? null : aiUserType.trim();
    }

    public String getAiIdCard() {
        return aiIdCard;
    }

    public void setAiIdCard(String aiIdCard) {
        this.aiIdCard = aiIdCard == null ? null : aiIdCard.trim();
    }
}