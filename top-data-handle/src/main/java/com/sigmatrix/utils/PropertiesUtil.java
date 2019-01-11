package com.sigmatrix.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	static Properties properties = new Properties();
	
	//加载property文件到io流里面  
    static{
        try {
            InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(is);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
    }  
  
    /** 
     * 根据key值取得对应的value值 
     * 
     * @param key 
     * @return 
     */  
    public static String getValue(String key) {
        return properties.getProperty(key);  
    }
}