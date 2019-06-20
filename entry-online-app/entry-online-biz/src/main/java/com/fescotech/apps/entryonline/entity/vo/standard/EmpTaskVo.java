package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.Date;

import com.fescotech.apps.entryonline.util.MyDateUtils;

public class EmpTaskVo {

	protected Long empTaskId;

	protected String taskId;

	protected String empName;

	protected String busiCustNo;

	protected String companyName;

	protected String idCode;

	protected String mobile;


	protected String empEid;

	protected String empStatus;

	protected Date startTime;

	protected String enrollStatus;

	protected String personnelStatus;

	protected String insStatus;
	
	
	protected String myOrderStartTime;
	
	
	
	
	

	

	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		setMyOrderStartTime(startTime);
	}


	public String getMyOrderStartTime() {
		return myOrderStartTime;
	}


	public Long getEmpTaskId() {
		return empTaskId;
	}

	public void setEmpTaskId(Long empTaskId) {
		this.empTaskId = empTaskId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getEmpEid() {
		return empEid;
	}

	public void setEmpEid(String empEid) {
		this.empEid = empEid;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	

	public String getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(String enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getPersonnelStatus() {
		return personnelStatus;
	}

	public void setPersonnelStatus(String personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	public String getInsStatus() {
		return insStatus;
	}

	public void setInsStatus(String insStatus) {
		this.insStatus = insStatus;
	}
	
	public void setMyOrderStartTime(Date date) {
		this.myOrderStartTime=MyDateUtils.formatDate(date);
	}

	
	

}