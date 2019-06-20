package com.fescotech.apps.entryonline.dto;

/**
 * Created by cy on 2018/3/2.
 */
public class EntrySituationDtoResp {

	private Integer empTaskId;
	private Integer empId;
	private String empName;
	private String busiCustNo;
	private String empDep;
	private String post;
	private Integer jobType;
	private String idCode;
	private String mobile;
	private Integer feePersonType;
	private Integer taskStatus;
	//入职登记手续办理状态
	private Integer enrollStatus;
	//社保手续办理状态
	private Integer insStatus;
	// 入职体检
	private Integer healthExamStatus;
	//档案手续办理状态
	private Integer personnelStatus;
	//备注
	private String remark;
	//员工状态
	private Integer empStatus;
	//订单开始时间
	private String startTime;
	// 劳动合同办理状态
	private Integer contractStatus;
	//店长hr确认状态码
	private Integer confirmStatus;
	//店长hr确认状态名
	private String confirmStatusName;
	//入职日期
	private String entryTime;
	//员工已经预排的公司
	private String orderCompany;
	private String orderBusiCustNo;
	//完成状态
	private String syncMisFlag;

	public String getSyncMisFlag() {
		return syncMisFlag;
	}

	public void setSyncMisFlag(String syncMisFlag) {
		this.syncMisFlag = syncMisFlag;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getOrderCompany() {
		return orderCompany;
	}

	public void setOrderCompany(String orderCompany) {
		this.orderCompany = orderCompany;
	}

	public String getOrderBusiCustNo() {
		return orderBusiCustNo;
	}

	public void setOrderBusiCustNo(String orderBusiCustNo) {
		this.orderBusiCustNo = orderBusiCustNo;
	}

	public Integer getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getConfirmStatusName() {
		return confirmStatusName;
	}

	public void setConfirmStatusName(String confirmStatusName) {
		this.confirmStatusName = confirmStatusName;
	}

	public Integer getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
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

	public String getEmpDep() {
		return empDep;
	}

	public void setEmpDep(String empDep) {
		this.empDep = empDep;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
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

	public Integer getFeePersonType() {
		return feePersonType;
	}

	public void setFeePersonType(Integer feePersonType) {
		this.feePersonType = feePersonType;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(Integer enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public Integer getInsStatus() {
		return insStatus;
	}

	public void setInsStatus(Integer insStatus) {
		this.insStatus = insStatus;
	}

	public Integer getHealthExamStatus() {
		return healthExamStatus;
	}

	public void setHealthExamStatus(Integer healthExamStatus) {
		this.healthExamStatus = healthExamStatus;
	}

	public Integer getPersonnelStatus() {
		return personnelStatus;
	}

	public void setPersonnelStatus(Integer personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
