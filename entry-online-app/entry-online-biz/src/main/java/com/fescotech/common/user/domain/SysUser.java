package com.fescotech.common.user.domain;

import java.util.Date;

public class SysUser implements java.io.Serializable {
	private static final long serialVersionUID = 8805950408410083034L;

	/**
	 * 用户ID
	 */
	private Long userId; 
	
	/**
	 * 登录账号
	 */
	private String loginName; 
	
	/**
	 * 用户名称
	 */
	private String userName; 
	 
	/**
	 * 地址
	 */
	private String address; 
	
	/**
	 * 电话(加区号，分机号)
	 */
	private String phone; 
	
	/**
	 * EMAIL
	 */
	private String email; 
	
	/**
	 * 用户密码
	 */
	private String userPwd; 
	
	/**
	 * 性别
	 */
	private Integer sex; 
	
	/**
	 * 用户描述
	 */
	private String userDesc; 
	
	/**
	 * 状态
	 */
	private Integer userStatus; 
	
	/**
	 * 创建时间
	 */
	private Date createTime; 
	
	/**
	 * 员工内部编号
	 */
	private String userNumber; 
 
	/**
	 * 手机号
	 */
	private String mobile; 
	
	/**
	 * 公司id
	 */
	private Long corpId; 
	
	/**
	 * 邮政编码
	 */
	private String postCode; 
	
	/**
	 * 用户类型(1用户 2公司管理 3超级管理)
	 */
	private Integer userType; 
	 
	/**
	 * 用户所属机构ID，行政意义 
	 */
	private Long orgId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getCorpId() {
		return corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	} 
}
