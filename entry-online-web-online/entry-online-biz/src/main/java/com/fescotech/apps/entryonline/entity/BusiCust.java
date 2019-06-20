package com.fescotech.apps.entryonline.entity;

public class BusiCust {
	private String busiCustNo;
	private String busiCustName;
	public BusiCust() {
		super();
	}
	public BusiCust(String busiCustNo, String busiCustName) {
		super();
		this.busiCustNo = busiCustNo;
		this.busiCustName = busiCustName;
	}
	public String getBusiCustNo() {
		return busiCustNo;
	}
	public void setBusiCustNo(String busiCustNo) {
		this.busiCustNo = busiCustNo;
	}
	public String getBusiCustName() {
		return busiCustName;
	}
	public void setBusiCustName(String busiCustName) {
		this.busiCustName = busiCustName;
	}
	
	
}
