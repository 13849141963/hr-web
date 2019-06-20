package com.fescotech.apps.admin.biz.admin;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestBo{
    @Test
    public void testRe() throws Exception {
       /* Class<?> clazz = Class.forName("com.fescotech.apps.admin.biz.admin.Student");
        System.out.println(Modifier.toString(clazz.getModifiers()));
        System.out.println(clazz.getSimpleName());
        //获取所有属性
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getName());
        }*/
        String string = "2016-10-24";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(string);

        Calendar currCal = Calendar.getInstance();
        currCal.setTime(date);
        int currentYear = currCal.get(Calendar.YEAR);
        System.out.println(currentYear);
        currCal.setTime(new Date());
        int currentYear2 = currCal.get(Calendar.YEAR);
        System.out.println(currentYear2);

        //clazz.geti
    }
}
