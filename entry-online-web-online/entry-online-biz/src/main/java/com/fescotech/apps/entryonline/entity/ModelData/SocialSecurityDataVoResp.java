package com.fescotech.apps.entryonline.entity.ModelData;

public class SocialSecurityDataVoResp {
    //员工入职任务id
    private Integer empTaskId = 1;
    //员工id
    private Integer empId= 1;
    //员工姓名
    private String empName= "1";
    //公司所属业务客户编号
    private String busiCustNo = "1";
    //证件类型
    private Integer idType= 1;
    //身份证id
    private String idCode = "1";
    //手机号
    private String mobile = "1";
    //员工状态
    private Integer empStatus= 1;
    //审核状态
    private Integer insStatus= 1;
    //社保类型
    private Integer insAddType= 1;
    //缴费类别
    private Integer payFeeType= 1;
    //员工类型 全日制或非全日制
    private Integer jobType= 1;
    //审核不通过原因
    private String insFailReason = "1";
    //是否转入变更
    private Integer intoChangFlag= 1;

    public SocialSecurityDataVoResp() {
        super();
    }
    public Integer getEmpTaskId() {
        return empTaskId;
    }
    public void setEmpTaskId(Integer empTaskId) {
        this.empTaskId = empTaskId;
    }
    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public String getBusiCustNo() {
        return busiCustNo;
    }
    public void setBusiCustNo(String busiCustNo) {
        this.busiCustNo = busiCustNo;
    }
    public Integer getIdType() {
        return idType;
    }
    public void setIdType(Integer idType) {
        this.idType = idType;
    }
    public String getIdCode() {
        return idCode;
    }
    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getEmpStatus() {
        return empStatus;
    }
    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
    }
    public Integer getInsStatus() {
        return insStatus;
    }
    public void setInsStatus(Integer insStatus) {
        this.insStatus = insStatus;
    }
    public Integer getInsAddType() {
        return insAddType;
    }
    public void setInsAddType(Integer insAddType) {
        this.insAddType = insAddType;
    }
    public Integer getPayFeeType() {
        return payFeeType;
    }
    public void setPayFeeType(Integer payFeeType) {
        this.payFeeType = payFeeType;
    }
    public Integer getJobType() {
        return jobType;
    }
    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }
    public String getInsFailReason() {
        return insFailReason;
    }
    public void setInsFailReason(String insFailReason) {
        this.insFailReason = insFailReason;
    }
    public Integer getIntoChangFlag() {
        return intoChangFlag;
    }
    public void setIntoChangFlag(Integer intoChangFlag) {
        this.intoChangFlag = intoChangFlag;
    }

}
