package com.aixl.m.model;

public class aiDoc {
    private String aiDocId;

    private String aiDocPwd;

    private Integer aiDocAge;

    private String aiDocSex;

    private String aiDocName;

    private String aiDocUnit;

    private String aiDocDepartment;

    private String aiDocPhone;

    private String aiDocSuperiorId;

    private Integer aiDocPatientNum;

    private String aiDocPhoto;

    private String aiUserType;

    private String aiIdCard;

    public String getAiDocId() {
        return aiDocId;
    }

    public void setAiDocId(String aiDocId) {
        this.aiDocId = aiDocId == null ? null : aiDocId.trim();
    }

    public String getAiDocPwd() {
        return aiDocPwd;
    }

    public void setAiDocPwd(String aiDocPwd) {
        this.aiDocPwd = aiDocPwd == null ? null : aiDocPwd.trim();
    }

    public Integer getAiDocAge() {
        return aiDocAge;
    }

    public void setAiDocAge(Integer aiDocAge) {
        this.aiDocAge = aiDocAge;
    }

    public String getAiDocSex() {
        return aiDocSex;
    }

    public void setAiDocSex(String aiDocSex) {
        this.aiDocSex = aiDocSex == null ? null : aiDocSex.trim();
    }

    public String getAiDocName() {
        return aiDocName;
    }

    public void setAiDocName(String aiDocName) {
        this.aiDocName = aiDocName == null ? null : aiDocName.trim();
    }

    public String getAiDocUnit() {
        return aiDocUnit;
    }

    public void setAiDocUnit(String aiDocUnit) {
        this.aiDocUnit = aiDocUnit == null ? null : aiDocUnit.trim();
    }

    public String getAiDocDepartment() {
        return aiDocDepartment;
    }

    public void setAiDocDepartment(String aiDocDepartment) {
        this.aiDocDepartment = aiDocDepartment == null ? null : aiDocDepartment.trim();
    }

    public String getAiDocPhone() {
        return aiDocPhone;
    }

    public void setAiDocPhone(String aiDocPhone) {
        this.aiDocPhone = aiDocPhone == null ? null : aiDocPhone.trim();
    }

    public String getAiDocSuperiorId() {
        return aiDocSuperiorId;
    }

    public void setAiDocSuperiorId(String aiDocSuperiorId) {
        this.aiDocSuperiorId = aiDocSuperiorId == null ? null : aiDocSuperiorId.trim();
    }

    public Integer getAiDocPatientNum() {
        return aiDocPatientNum;
    }

    public void setAiDocPatientNum(Integer aiDocPatientNum) {
        this.aiDocPatientNum = aiDocPatientNum;
    }

    public String getAiDocPhoto() {
        return aiDocPhoto;
    }

    public void setAiDocPhoto(String aiDocPhoto) {
        this.aiDocPhoto = aiDocPhoto == null ? null : aiDocPhoto.trim();
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