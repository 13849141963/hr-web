package com.fescotech.apps.entryonline.util;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
	
	/** 
     * 判断是否为浮点数或者整数 
     * @param str 
     * @return true Or false 
     */  
    public static boolean isNumeric(String str){  
          Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");  
          Matcher isNum = pattern.matcher(str);  
          if( !isNum.matches() ){  
                return false;  
          }  
          return true;  
    }  
      
    /** 
     * 判断是否为正确的邮件格式 
     * @param str 
     * @return boolean 
     */  
    public static boolean isEmail(String str){  
        if(isEmpty(str))  
            return false;  
        return str.matches("^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$");  
    }  
      
    /** 
     * 判断字符串是否为合法手机号 11位 13 14 15 18开头 
     * @param str 
     * @return boolean 
     */  
    public static boolean isMobile(String str){  
        if(isEmpty(str))  
            return false;  
        return str.matches("^(13|14|15|18)\\d{9}$");  
    }  
      
    /** 
     * 判断是否为数字 
     * @param str 
     * @return 
     */  
    public static boolean isNumber(String str) {  
        try{  
            Integer.parseInt(str);  
            return true;  
        }catch(Exception ex){  
            return false;  
        }  
    }  
      
          
    /** 
     * 判断字符串是否为非空(包含null与"") 
     * @param str 
     * @return 
     */  
    public static boolean isNotEmpty(String str){  
        if(str == null || "".equals(str))  
            return false;  
        return true;  
    }  
      
    /** 
     * 判断字符串是否为非空(包含null与"","    ") 
     * @param str 
     * @return 
     */  
    public static boolean isNotEmptyIgnoreBlank(String str){  
        if(str == null || "".equals(str) || "".equals(str.trim()))  
            return false;  
        return true;  
    }  
      
    /** 
     * 判断字符串是否为空(包含null与"") 
     * @param str 
     * @return 
     */  
    public static boolean isEmpty(String str){  
        if(str == null || "".equals(str))  
            return true;  
        return false;  
    }  
      
    /** 
     * 判断字符串是否为空(包含null与"","    ") 
     * @param str 
     * @return 
     */  
    public static boolean isEmptyIgnoreBlank(String str){  
        if(str == null || "".equals(str) || "".equals(str.trim()))  
            return true;  
        return false;  
    }  
    /** 
     * 判断对象是否为空
     * @param obj
     * @return 
     */ 
    public static boolean objectIsEmpty(Object obj) {
    	// 判断参数是否为空或者''  
        if (obj == null || "".equals(obj)) {  
            return true;  
        } else if ("java.lang.String".equals(obj.getClass().getName())){  
            // 判断传入的参数的String类型的  
  
            // 替换各种空格  
            String tmobj = Pattern.compile("\\r|\\n|\\u3000")  
                                     .matcher((String)obj).replaceAll("");  
            // 匹配空  
            return Pattern.compile("^(\\s)*$")  
                          .matcher(tmobj).matches();  
        } else {  
            // 方法类  
            Method method = null;  
            String newInput = "";  
            try {  
                // 访问传入参数的size方法  
                method = obj.getClass().getMethod("size");  
                // 判断size大小  
  
                // 转换为String类型  
                newInput = String.valueOf(method.invoke(obj));  
                // size为0的场合  
                if (Integer.parseInt(newInput) == 0) {  
  
                    return true;  
                } else {  
  
                    return false;  
                }  
            } catch (Exception e) {  
                // 访问失败  
                try {  
                    // 访问传入参数的getItemCount方法  
                    method = obj.getClass().getMethod("getItemCount");  
                    // 判断size大小  
                      
                    // 转换为String类型  
                    newInput = String.valueOf(method.invoke(obj));  
                      
                    // getItemCount为0的场合  
                    if (Integer.parseInt(newInput) == 0) {  
  
                        return true;  
                    } else {  
  
                        return false;  
                    }  
                } catch (Exception ex) {  
                    // 访问失败  
                    try{  
                        // 判断传入参数的长度  
  
                        // 长度为0的场合  
                        if (Array.getLength(obj) == 0) {  
  
                            return true;  
                        } else {  
  
                            return false;  
                        }  
                    } catch (Exception exx) {  
                        // 访问失败  
                        try{  
                            // 访问传入参数的hasNext方法  
                            method = Iterator.class.getMethod("hasNext");  
                            // 转换String类型  
                            newInput = String.valueOf(method.invoke(obj));  
                              
                            // 转换hasNext的值  
                            if (!Boolean.valueOf(newInput)) {  
                                return true;  
                            } else {  
                                return false;  
                            }  
                              
                        } catch (Exception exxx) {  
                            // 以上场合不满足  
                              
                            return false;  
                        }  
                    }  
                }  
            }  
        }  
    }
    
    public static void main(String[] args) {
		String str = " ";
		List s = new ArrayList();
		s.add(" ");
	}

}
