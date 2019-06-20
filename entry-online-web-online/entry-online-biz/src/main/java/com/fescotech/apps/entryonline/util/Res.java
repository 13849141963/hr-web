package com.fescotech.apps.entryonline.util;
import com.fesco.pafa.dto.FResponse;
/**
 * 通用返回json数据
 */
public  class  Res  extends FResponse {
	 private final static String ERROR_CODE="1";
	 private final static String OK_CODE="0";	 
	 public static Res error(String code,String msg){
		 Res res = new Res();
		 res.setSuccess(false);
		 res.setMessage(msg);
		 res.setRspCode(code);		 
		 return res;
	 }
	 public static Res error(String msg){		   
		 return error(ERROR_CODE,msg);
	 }
	 
	 public static Res error(){		   
		 return error("2","未知异常，请联系管理员");
	 }
	 
	 public static Res ok(String code,String msg,Object data){		   
		 Res res = new Res();
		 res.setSuccess(true);
		 res.setMessage(msg);
		 res.setRspCode(code);		 
		 res.setData(data);
		 return res;
	 }
	 
	 public static Res ok(Object data){
		 return ok(OK_CODE,null,data);
	 }
	 
	 public static Res ok(String msg,Object data){
		 return ok(OK_CODE,msg,data);
	 }
	 
	 public static Res ok(){
		 return ok(OK_CODE,null,null);
	 }
}