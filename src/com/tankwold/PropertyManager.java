package com.tankwold;

import java.io.IOException;
import java.util.Properties;

/**
 * @Describe: 用于管理配置文件
 * @Author Happy
 * @Create 2022/5/22-17:29
 **/
public class PropertyManager {
    static Properties properties = new Properties();
    
    static {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //取键值
    public static Object getValue(String key) {
        if (key == null) {
            return properties;
        }
        return properties.get(key);
    }
    
    public static int getInteger(String key) {
        if (key == null) {
            return 0;
        }
        return Integer.parseInt((String) properties.get(key));
    }
    
    public static String getString(String key) {
        if (key == null || key.equals("")) {
            return "";
        }
        return (String) properties.get(key);
    }
}
