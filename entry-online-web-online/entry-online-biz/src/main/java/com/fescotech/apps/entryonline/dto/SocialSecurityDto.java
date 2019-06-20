package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/2/11.
 */
public class SocialSecurityDto {
    private String[] busiCustNos;
    private Integer insStatus;
    private Integer empStatus;
    private String mobile;
    private String idCode;
    private String empEid;
    private String empName;
    private Integer intoChangeFlag;
    private Integer jobType;
    private Integer pageSize;
    private Integer pageNo;

    public String[] getBusiCustNos() {
        return busiCustNos;
    }

    public void setBusiCustNos(String[] busiCustNos) {
        this.busiCustNos = busiCustNos;
    }

    public Integer getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }

    public Integer getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getEmpEid() {
        return empEid;
    }

    public void setEmpEid(String empEid) {
        this.empEid = empEid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getIntoChangeFlag() {
        return intoChangeFlag;
    }

    public void setIntoChangeFlag(Integer intoChangeFlag) {
        this.intoChangeFlag = intoChangeFlag;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
