package com.fescotech.apps.entryonline.entity.vo;

public class CustomerVo {
	private String custId;
	private String custName;
	private String busiCustId;
	private String busiCustName;
	private String custConId;
	private String contName;
	private String conAcceId;
	private String acceName;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getBusiCustId() {
		return busiCustId;
	}

	public void setBusiCustId(String busiCustId) {
		this.busiCustId = busiCustId;
	}

	public String getBusiCustName() {
		return busiCustName;
	}

	public void setBusiCustName(String busiCustName) {
		this.busiCustName = busiCustName;
	}

	public String getCustConId() {
		return custConId;
	}

	public void setCustConId(String custConId) {
		this.custConId = custConId;
	}

	public String getConAcceId() {
		return conAcceId;
	}

	public void setConAcceId(String conAcceId) {
		this.conAcceId = conAcceId;
	}

	public String getAcceName() {
		return acceName;
	}

	public void setAcceName(String acceName) {
		this.acceName = acceName;
	}

	public CustomerVo() {
		// TODO Auto-generated constructor stub
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public CustomerVo(String custId, String custName, String busiCustId,
			String busiCustName, String custConId, String contName,
			String conAcceId, String acceName) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.busiCustId = busiCustId;
		this.busiCustName = busiCustName;
		this.custConId = custConId;
		this.contName = contName;
		this.conAcceId = conAcceId;
		this.acceName = acceName;
	}

}
