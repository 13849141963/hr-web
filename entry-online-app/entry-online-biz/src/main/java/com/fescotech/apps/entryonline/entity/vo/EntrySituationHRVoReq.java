package com.fescotech.apps.entryonline.entity.vo;

/**
 * Created by cy on 2018/3/3.
 */
public class EntrySituationHRVoReq {
	//业务客户编号集合
    private String[] busiCustNos;
    //入职办理状态
    private Integer taskStatus;
    private Integer intoChangeFlag;
    private Integer empStatus;
    private Integer jobType;
    //员工职务
    private Integer empType;
    private String beginDate;
    private String endDate;
    private Integer SocialSecurityType;
    private Integer userInfoType;
    private String userInfoContent;
    private Integer page;
    private Integer rows;

    public String[] getBusiCustNos() {
        return busiCustNos;
    }

    public void setBusiCustNos(String[] busiCustNos) {
        this.busiCustNos = busiCustNos;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getIntoChangeFlag() {
        return intoChangeFlag;
    }

    public void setIntoChangeFlag(Integer intoChangeFlag) {
        this.intoChangeFlag = intoChangeFlag;
    }

    public Integer getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getEmpType() {
        return empType;
    }

    public void setEmpType(Integer empType) {
        this.empType = empType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getSocialSecurityType() {
        return SocialSecurityType;
    }

    public void setSocialSecurityType(Integer socialSecurityType) {
        SocialSecurityType = socialSecurityType;
    }

    public Integer getUserInfoType() {
        return userInfoType;
    }

    public void setUserInfoType(Integer userInfoType) {
        this.userInfoType = userInfoType;
    }

    public String getUserInfoContent() {
        return userInfoContent;
    }

    public void setUserInfoContent(String userInfoContent) {
        this.userInfoContent = userInfoContent;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
