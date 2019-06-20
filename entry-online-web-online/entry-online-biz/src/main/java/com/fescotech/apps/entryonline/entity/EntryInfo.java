package com.fescotech.apps.entryonline.entity;

import java.util.Date;

public class EntryInfo {
	// 任务id
	private Integer taskId;
	// 姓名
	private String empName;
	// 所在公司
	private String company;
	// 身份证号
	private String idCode;
	// 手机号
	private String mobile;
	// 员工状态
	private Integer employeeState;
	// 订单起始时间
	private Date orderTime;
	// 社保手续
	private Integer shebaoProcedures;
	// 入职登记
	private Integer entryRegister;
	// 档案手续
	private Integer danganProcedures;
	// 入职体检
	private Integer entryTest;
	// 劳动合同
	private Integer laodongHetong;
	// 备注
	private String remark;
	//店长hr确认状态码
	private Integer confirmStatus;
	//店长hr确认状态名
	private String confirmStatusName;
	//入职时间
	private String entryTime;
	//员工已预排公司
	private String orderCompany;
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

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Integer getEmployeeState() {
		return employeeState;
	}

	public void setEmployeeState(Integer employeeState) {
		this.employeeState = employeeState;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getShebaoProcedures() {
		return shebaoProcedures;
	}

	public void setShebaoProcedures(Integer shebaoProcedures) {
		this.shebaoProcedures = shebaoProcedures;
	}

	public Integer getEntryRegister() {
		return entryRegister;
	}

	public void setEntryRegister(Integer entryRegister) {
		this.entryRegister = entryRegister;
	}

	public Integer getDanganProcedures() {
		return danganProcedures;
	}

	public void setDanganProcedures(Integer danganProcedures) {
		this.danganProcedures = danganProcedures;
	}

	public Integer getEntryTest() {
		return entryTest;
	}

	public void setEntryTest(Integer entryTest) {
		this.entryTest = entryTest;
	}

	public Integer getLaodongHetong() {
		return laodongHetong;
	}

	public void setLaodongHetong(Integer laodongHetong) {
		this.laodongHetong = laodongHetong;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
