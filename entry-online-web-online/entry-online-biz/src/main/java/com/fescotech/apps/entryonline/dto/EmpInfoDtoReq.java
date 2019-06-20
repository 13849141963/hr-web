package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/26.
 */
public class EmpInfoDtoReq {
    //业务客户编号集合
    private String[] busiCustNos;
    //入职任务id集合
    private Long[] empTaskIds;
    //执行月份
    private Integer handleMonth;
    //是否转入变更
    private Integer intoChangeFlag;
    //员工状态
    private Integer empStatus;
    //用工类型(全日制/非全日制)
    private Integer jobType;
    //员工职务
    private Integer post;
    //社保新增类型
    private Integer insAddType;
    //入职办理状态
    private Integer taskStatus;
    //手机号
    private String mobile;
    //身份证号
    private String idCode;
    //雇员唯一id
    private Integer empEid;
    //员工姓名
    private String empName;
    //入职开始时间
    private String entryStartTime;
    //入职结束时间
    private String entryEndTime;
    //同步状态
    private Integer syncMisFlag;

    public String[] getBusiCustNos() {
        return busiCustNos;
    }

    public void setBusiCustNos(String[] busiCustNos) {
        this.busiCustNos = busiCustNos;
    }

    public Long[] getEmpTaskIds() {
        return empTaskIds;
    }

    public void setEmpTaskIds(Long[] empTaskIds) {
        this.empTaskIds = empTaskIds;
    }

    public Integer getHandleMonth() {
        return handleMonth;
    }

    public void setHandleMonth(Integer handleMonth) {
        this.handleMonth = handleMonth;
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

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public Integer getInsAddType() {
        return insAddType;
    }

    public void setInsAddType(Integer insAddType) {
        this.insAddType = insAddType;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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

    public Integer getEmpEid() {
        return empEid;
    }

    public void setEmpEid(Integer empEid) {
        this.empEid = empEid;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEntryStartTime() {
        return entryStartTime;
    }

    public void setEntryStartTime(String entryStartTime) {
        this.entryStartTime = entryStartTime;
    }

    public String getEntryEndTime() {
        return entryEndTime;
    }

    public void setEntryEndTime(String entryEndTime) {
        this.entryEndTime = entryEndTime;
    }

    public Integer getSyncMisFlag() {
        return syncMisFlag;
    }

    public void setSyncMisFlag(Integer syncMisFlag) {
        this.syncMisFlag = syncMisFlag;
    }
}
