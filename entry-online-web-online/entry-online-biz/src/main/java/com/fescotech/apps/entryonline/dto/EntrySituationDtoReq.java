package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/2.
 */
public class EntrySituationDtoReq {
	private String[] busiCustNos;
	private Integer intoChangeFlag;
	// 员工状态
	private Integer empStatus;
	// 员工类型
	private Integer empType;
	private Integer jobType;
	// 员工职务类型
	private Integer post;
	// 社保新增类型(转入\新增)
	private Integer insAddType;
	// 执行月份
	private String handleMonth;

	private Integer taskStatus;
	private String mobile;
	private String idCode;
	private String empEid;
	private String empName;
	private Integer pageSize;
	private Integer pageNo;

	private String entryStartTime;
	private String entryEndTime;

	//完成状态
	private Integer syncMisFlag;

	public Integer getSyncMisFlag() {
		return syncMisFlag;
	}

	public void setSyncMisFlag(Integer syncMisFlag) {
		this.syncMisFlag = syncMisFlag;
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

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	public Integer getEmpType() {
		return empType;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
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

	public String getHandleMonth() {
		return handleMonth;
	}

	public void setHandleMonth(String handleMonth) {
		this.handleMonth = handleMonth;
	}

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
