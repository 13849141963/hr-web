package com.fescotech.apps.entryonline.entity.vo;

import java.util.Date;

/**
 * Created by cy on 2018/3/2.
 */
public class EntrySituationVoReq {
	private String[] busiCustNos;
	private Integer taskStatus;
	private Integer intoChangeFlag;
	private Integer empStatus;
	private Integer jobType;
	private Integer empType;
	private String beginDate;
	private String endDate;
	private Integer SocialSecurityType;
	private Integer userInfoType;
	private String userInfoContent;
	private Integer page;
	private Integer rows;

	// 员工职务类型
	private Integer post;
	// 社保新增类型(转入\新增)
	private Integer insAddType;

	// 执行月份
	private String handleMonth;

	// 手机号
	private String mobile;
	// 身份证号
	private String idCode;

	// 员工姓名
	private String empName;
	//完成状态
	private Integer syncMisFlag;

	public Integer getSyncMisFlag() {
		return syncMisFlag;
	}

	public void setSyncMisFlag(Integer syncMisFlag) {
		this.syncMisFlag = syncMisFlag;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
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

	public Integer getEmpType() {
		return empType;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSocialSecurityType() {
		return SocialSecurityType;
	}

	public void setSocialSecurityType(Integer socialSecurityType) {
		SocialSecurityType = socialSecurityType;
	}

	public Integer getUserInfoType() {
		return userInfoType;
	}

	public void setUserInfoType(Integer userInfoType) {
		this.userInfoType = userInfoType;
	}

	public String getUserInfoContent() {
		return userInfoContent;
	}

	public void setUserInfoContent(String userInfoContent) {
		this.userInfoContent = userInfoContent;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
