package com.fescotech.apps.entryonline.entity;

import java.io.Serializable;

public class WebUserVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * HR帐号(登录名)
	 */
	private String loginName;

	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * Hr商户名称
	 */
	private String fullName;
	/**
	 * 电子邮件
	 */
	private String email;
	/**
	 * 账户状态
	 */
	private String status;
	
	
	private Integer dutyType;
	
	private Integer userType;
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getDutyType() {
		return dutyType;
	}

	public void setDutyType(Integer dutyType) {
		this.dutyType = dutyType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public WebUserVo() {
		super();
	}

	public WebUserVo(String loginName, String mobile, String fullName,
			String email, String status) {
		super();
		this.loginName = loginName;
		this.mobile = mobile;
		this.fullName = fullName;
		this.email = email;
		this.status = status;
	}

}
