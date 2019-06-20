package com.fescotech.apps.entryonline.common.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by YJ on 2017/12/4.
 */
public class MiddlePropertiesUtils {
    private static Properties p = new Properties();
    static {
        try{
            p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/middle.properties"));
        }catch(IOException e){
            System.out.println("读取中间库配置信息时出错！");
        }
    }

    /**
     * 根据key得到value的值
     * */
    public static String getValue(String key){
        return p.getProperty(key);
    }
}
