package com.fescotech.apps.entryonline.util;
import java.io.Serializable;

/**
 * api返回客户端对象
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -8688072985562369039L;
	public static final String SUCCESS = "0";
	public static final String FAIL = "1";
    private String code;
    private String errorMsg;
    private T successResult;

    public Result(String code, String errorMsg, T successResult) {
        super();
        this.code = code;
        this.errorMsg = errorMsg;
        this.successResult = successResult;
    }

    public Result() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(T successResult) {
        this.successResult = successResult;
    }
    
	/***
	 * 创建成功结果
	 * @param t 结果对象
	 * @return
	 */
	public static Result createSuccessResult(Object object){
		return new Result(Result.SUCCESS, null, object);
	}

	/***
	 * 创建成功结果
	 * @return
	 */
	public static Result createSuccessResult(){
		return new Result(Result.SUCCESS, null, null);
	}
	
	/***
	 * 创建失败结果
	 * @param msg 错误消息
	 * @return
	 */
	public static  Result  createFailResult(String msg){
		return new Result (Result.FAIL, msg, null);
	}
	
	/***
	 * 创建失败结果
	 * @param code 错误代码
	 * @param msg 错误消息
	 * @return
	 */
	public static  Result  createFailResult(String code, String msg){
		return new Result (code, msg, null);
	}
	
	/***
	 * 创建失败结果
	 * @param code 错误代码
	 * @param msg 错误消息
	 * @param obj 结果对象
	 * @return
	 */
	public static  Result  createFailResult(String code, String msg, Object obj){
		return new Result (code, msg, obj);
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", errorMsg=" + errorMsg + ", successResult=" + successResult + "]";
	}

}
