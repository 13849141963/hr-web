package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/3.
 */
public class EntrySituationHRDtoReq {
    private String[] busiCustNos;
    //多个入职任务id
    private Long[] empTaskIds;
    //入职办理状态
    private Integer taskStatus;
    //用工类型(全日制/非全日制)
    private Integer jobType;
    //员工状态
    private Integer post;
    //手机号
    private String mobile;
    //身份证号
    private String idCode;
    //员工内部编号(搜索条件暂时没有)
    private String empEid;
    //员工姓名
    private String empName;
    private Integer pageSize;
    private Integer pageNo;

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

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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
