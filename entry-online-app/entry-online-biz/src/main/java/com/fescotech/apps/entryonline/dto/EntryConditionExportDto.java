package com.fescotech.apps.entryonline.dto;

public class EntryConditionExportDto {
	// 雇员入职标识
	private Integer empTaskId;
	// 雇员id
	private Integer empId;
	// 员工姓名
	private String empName;
	// 身份证号
	private String idCode;
	// 手机号
	private String mobile;
	// 业务客户编号(品牌号)
	private String busiCustNo;
	//公司名称(品牌号)
	private String companyName;
	// 店号
	private String empDep;
	// 店名
	private String depName;
	// 店长手机号
	private String mgrMobile;
	// 职务
	private Integer post;
	// 职务名称
	private String postName;
	// 用工类型
	private Integer jobType;
	//用工类型名称
	private String jobTypeName;
	//人员类型
	private Integer empType;
	private String empTypeName;
	// 是否需要三方协议
	private Integer isNeedThreeProtocol;
	private String isNeedThreeProtocolName;

	// 是否粮农
	private Integer isFarmer;
	private String isFarmerName;

	// 社保新增类型
	private Integer insAddType;
	private String insAddTypeName;

	// 缴费人员类别
	private Integer feePersonType;

	private String feePersonTypeName;
	// 是否转入变更
	private Integer intoChangeFlag;
	private String intoChangeFlagName;

	// 入职时间
	private String entryTime;
	// 是否需要缴纳公积金
	private Integer isNeedInsurance;

	private String isNeedInsuranceName;
	// 所有手续办理状态
	private Integer taskStatus;

	private String taskStatusName;

	//入职登记手续办理状态
	private Integer enrollStatus;
	private String enrollStatusName;
	
	//社保手续办理状态
	private Integer insStatus;
	private String insStatusName;
	
	//档案手续办理状态
	private Integer personnelStatus;
	private String personnelStatusName;
	//店长hr确认状态码
	private Integer confirmStatus;
	//店长hr确认状态名
	private String confirmStatusName;
	//员工已预派的公司
	private String orderCompany;
	//员工已预派的公司编号
	private String orderBusiCustNo;

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

	public String getJobTypeName() {
		return jobTypeName;
	}

	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}

	public String getIsNeedThreeProtocolName() {
		return isNeedThreeProtocolName;
	}

	public void setIsNeedThreeProtocolName(String isNeedThreeProtocolName) {
		this.isNeedThreeProtocolName = isNeedThreeProtocolName;
	}

	public String getIsFarmerName() {
		return isFarmerName;
	}

	public void setIsFarmerName(String isFarmerName) {
		this.isFarmerName = isFarmerName;
	}

	public String getInsAddTypeName() {
		return insAddTypeName;
	}

	public void setInsAddTypeName(String insAddTypeName) {
		this.insAddTypeName = insAddTypeName;
	}

	public String getFeePersonTypeName() {
		return feePersonTypeName;
	}

	public void setFeePersonTypeName(String feePersonTypeName) {
		this.feePersonTypeName = feePersonTypeName;
	}

	public String getIntoChangeFlagName() {
		return intoChangeFlagName;
	}

	public void setIntoChangeFlagName(String intoChangeFlagName) {
		this.intoChangeFlagName = intoChangeFlagName;
	}

	public String getIsNeedInsuranceName() {
		return isNeedInsuranceName;
	}

	public void setIsNeedInsuranceName(String isNeedInsuranceName) {
		this.isNeedInsuranceName = isNeedInsuranceName;
	}

	public String getTaskStatusName() {
		return taskStatusName;
	}

	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
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

	public String getMgrMobile() {
		return mgrMobile;
	}

	public void setMgrMobile(String mgrMobile) {
		this.mgrMobile = mgrMobile;
	}

	public Integer getPost() {
		return post;
	}

	public void setPost(Integer post) {
		this.post = post;
	}

	public Integer getJobType() {
		return jobType;
	}

	public void setJobType(Integer jobType) {
		this.jobType = jobType;
	}

	public Integer getIsNeedThreeProtocol() {
		return isNeedThreeProtocol;
	}

	public void setIsNeedThreeProtocol(Integer isNeedThreeProtocol) {
		this.isNeedThreeProtocol = isNeedThreeProtocol;
	}

	public Integer getIsFarmer() {
		return isFarmer;
	}

	public void setIsFarmer(Integer isFarmer) {
		this.isFarmer = isFarmer;
	}

	public Integer getInsAddType() {
		return insAddType;
	}

	public void setInsAddType(Integer insAddType) {
		this.insAddType = insAddType;
	}

	public Integer getFeePersonType() {
		return feePersonType;
	}

	public void setFeePersonType(Integer feePersonType) {
		this.feePersonType = feePersonType;
	}

	public Integer getIntoChangeFlag() {
		return intoChangeFlag;
	}

	public void setIntoChangeFlag(Integer intoChangeFlag) {
		this.intoChangeFlag = intoChangeFlag;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public Integer getIsNeedInsurance() {
		return isNeedInsurance;
	}

	public void setIsNeedInsurance(Integer isNeedInsurance) {
		this.isNeedInsurance = isNeedInsurance;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public Integer getEmpType() {
		return empType;
	}

	public void setEmpType(Integer empType) {
		this.empType = empType;
	}

	public String getEmpTypeName() {
		return empTypeName;
	}

	public void setEmpTypeName(String empTypeName) {
		this.empTypeName = empTypeName;
	}

	public Integer getEnrollStatus() {
		return enrollStatus;
	}

	public void setEnrollStatus(Integer enrollStatus) {
		this.enrollStatus = enrollStatus;
	}

	public String getEnrollStatusName() {
		return enrollStatusName;
	}

	public void setEnrollStatusName(String enrollStatusName) {
		this.enrollStatusName = enrollStatusName;
	}

	public Integer getInsStatus() {
		return insStatus;
	}

	public void setInsStatus(Integer insStatus) {
		this.insStatus = insStatus;
	}

	public String getInsStatusName() {
		return insStatusName;
	}

	public void setInsStatusName(String insStatusName) {
		this.insStatusName = insStatusName;
	}

	public Integer getPersonnelStatus() {
		return personnelStatus;
	}

	public void setPersonnelStatus(Integer personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	public String getPersonnelStatusName() {
		return personnelStatusName;
	}

	public void setPersonnelStatusName(String personnelStatusName) {
		this.personnelStatusName = personnelStatusName;
	}

	
}
