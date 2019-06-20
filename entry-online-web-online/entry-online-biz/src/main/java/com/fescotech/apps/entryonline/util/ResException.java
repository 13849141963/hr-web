package com.fescotech.apps.entryonline.util;

/**
 * 自定义异常
 */
public class ResException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private String code = "1";
    
    public ResException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ResException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public ResException(String msg, String code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public ResException(String msg, String code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
