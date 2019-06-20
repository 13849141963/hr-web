package webservice.test;

import java.io.Serializable;

/**
 * 返回对象
 * @author qiujian
 *
 */
public class WebserviceResult implements Serializable{
	private static final long serialVersionUID = -8688072985562369039L;
	
	public static final String SUCCESS_CODE = "0";
	public static final String FORMAT_JSON = "json";
	public static final String FORMAT_JSONP = "jsonp";
	
	private String code;
	private String errorMsg;
	private Object successResult;
	private Object errorResult;
	private String status;
	private String piCallRecId;
	private String errorStack;
	
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
	public Object getSuccessResult() {
		return successResult;
	}
	public void setSuccessResult(Object successResult) {
		this.successResult = successResult;
	}
	public Object getErrorResult() {
		return errorResult;
	}
	public void setErrorResult(Object errorResult) {
		this.errorResult = errorResult;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPiCallRecId() {
		return piCallRecId;
	}
	public void setPiCallRecId(String piCallRecId) {
		this.piCallRecId = piCallRecId;
	}

	public String getErrorStack() {
		return errorStack;
	}

	public WebserviceResult setErrorStack(String errorStack) {
		this.errorStack = errorStack;
		return this;
	}

	@Override
	public String toString() {
		return "webservice.test.WebserviceResult{" +
				"code='" + code + '\'' +
				", errorMsg='" + errorMsg + '\'' +
				", successResult=" + successResult +
				", errorResult=" + errorResult +
				", status='" + status + '\'' +
				", piCallRecId='" + piCallRecId + '\'' +
				", errorStack='" + errorStack + '\'' +
				'}';
	}

	public static WebserviceResult createError(String message){
		WebserviceResult error = new WebserviceResult();
		error.setCode("-999");
		error.setErrorMsg(message);
		return error;
	}
	public static WebserviceResult createError(String code,String message,String errorResult){
		WebserviceResult error = new WebserviceResult();
		error.setCode(code);
		error.setErrorMsg(message);
		error.setErrorResult(errorResult);
		return error;
		
	}
	public static WebserviceResult createError(String code,String message,String errorResult,String errorStack){
		WebserviceResult error = new WebserviceResult();
		error.setCode(code);
		error.setErrorMsg(message);
		error.setErrorResult(errorResult);
		error.setErrorStack(errorStack);
		return error;

	}
	public int getResultFlag(){
		if( SUCCESS_CODE.equals(getCode()) ){
			return (1);
		}else{
			return (0);
		}
		
	}
}
