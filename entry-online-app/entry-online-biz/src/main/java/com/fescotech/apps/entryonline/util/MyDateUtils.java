package com.fescotech.apps.entryonline.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {
 public static String formatDate(Date date){
	 if(date!=null){
		 DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//多态
		   return bf.format(date);//格式化 bf.format(date);
	 }else {
		return "";
	}

 }
 
 public static String formatDayDate(Date date){
	 if(date!=null){
		 DateFormat bf = new SimpleDateFormat("yyyy-MM-dd");//多态
		 return bf.format(date);//格式化 bf.format(date);
	 }else {
		 return "";
	 }
	 
 }
 
 public static Date parseString(String str){
	 if(str==null||"".equals(str)){
		 return null;
	 }
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 try {
		return sdf.parse(str);
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	 return null;
 }
}
