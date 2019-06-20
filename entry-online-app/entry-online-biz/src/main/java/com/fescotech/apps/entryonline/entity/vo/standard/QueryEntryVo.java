package com.fescotech.apps.entryonline.entity.vo.standard;

import java.util.List;

public class QueryEntryVo {
	private List<String> busiCustNos;
	private String companyName;
	private List<Long> empTaskIds;
	private Integer empStatus;
	private String employeeType;
	private Integer procedureType;
	private Integer procedureStatus;
	private String orderStartTime;
	private String orderEndTime;
	private String mobile;
	private String idCode;
	private Integer empEid;
	private String empName;
	private Integer pageSize;
	private Integer pageNo;
	private Integer bizType;
	private String taskId;
	private String creator;
	
	
	
	
	
	
	
	
	
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public List<String> getBusiCustNos() {
		return busiCustNos;
	}
	public void setBusiCustNos(List<String> busiCustNos) {
		this.busiCustNos = busiCustNos;
	}
	public List<Long> getEmpTaskIds() {
		return empTaskIds;
	}
	public void setEmpTaskIds(List<Long> empTaskIds) {
		this.empTaskIds = empTaskIds;
	}
	public Integer getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public Integer getProcedureType() {
		return procedureType;
	}
	public void setProcedureType(Integer procedureType) {
		this.procedureType = procedureType;
	}
	public Integer getProcedureStatus() {
		return procedureStatus;
	}
	public void setProcedureStatus(Integer procedureStatus) {
		this.procedureStatus = procedureStatus;
	}
	public String getOrderStartTime() {
		return orderStartTime;
	}
	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}
	public String getOrderEndTime() {
		return orderEndTime;
	}
	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
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
	public Integer getBizType() {
		return bizType;
	}
	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}
	
	

}
