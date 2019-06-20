package com.fescotech.apps.entryonline.entity;

public class EntryInfoHr {
	// 任务id
	private Integer taskId;
	// 姓名
	private String empName;
	// 品牌
	private String brand;
	// 店名
	private String dianming;
	// 职务
	private Integer zhiwu;
	// 用工类型
	private Integer yonggongType;
	// 身份证号
	private String idCode;
	// 手机号
	private String mobile;
	// 社保手续
	private Integer shebaoProcedures;
	// 入职登记
	private Integer entryRegister;
	// 人事档案
	private Integer personalRecords;
	//店长hr确认状态码
	private Integer confirmStatus;
	//店长hr确认状态名
	private String confirmStatusName;

	//店名
	private String empDepName;

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

	public String getEmpDepName() {
		return empDepName;
	}

	public void setEmpDepName(String empDepName) {
		this.empDepName = empDepName;
	}

	public EntryInfoHr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPersonalRecords() {
		return personalRecords;
	}

	public void setPersonalRecords(Integer personalRecords) {
		this.personalRecords = personalRecords;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDianming() {
		return dianming;
	}

	public void setDianming(String dianming) {
		this.dianming = dianming;
	}

	public Integer getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(Integer zhiwu) {
		this.zhiwu = zhiwu;
	}

	public Integer getYonggongType() {
		return yonggongType;
	}

	public void setYonggongType(Integer yonggongType) {
		this.yonggongType = yonggongType;
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

}
