package com.fescotech.apps.entryonline.dto;


public class WebUserDto {
	private String hrAccount;
	
	private String password;
	
	//private int userType;

/*	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}*/

	public String getHrAccount() {
		return hrAccount;
	}

	public void setHrAccount(String hrAccount) {
		this.hrAccount = hrAccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WebUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebUserDto(String hrAccount, String password) {
		super();
		this.hrAccount = hrAccount;
		this.password = password;
	}
	
}
