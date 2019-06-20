package com.fescotech.apps.entryonline.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_YEAR_PATTERN = "yyyy";
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN_2 = "YYYYMMDD";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String formatToStr(Date date) {
        return formatToStr(date, DATE_PATTERN);
    }

    public static String formatToStr(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date formatToDate(Date date) {
         return formatToDate(date,DATE_PATTERN);
    }

    public static Date formatToDate(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            String str = df.format(date);
            try {
                return df.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * 获取当年第一天日期
     * */
    public static Date getCurrYearFirst(){
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年最后一天日期
     * */
    public static Date getCurrYearLast(){
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某一年第一天日期
     * */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某一年最后一天日期
     * */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR,year);
        calendar.roll(Calendar.DAY_OF_YEAR,-1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }


//YJ
 /*   public static void main (String[] arge){
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatToStr(getCurrYearLast(),DATE_TIME_PATTERN));
    }*/
}


