package com.zhan.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {//调用配置文件，修改配置文件就能直接修改程序对应viriable
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key) {
        if (props == null) return null;
        return props.get(key);
    }
    public static String getString(String key) {
        return (String)get(key);
    }
    public static int getInt(String key) {
        return Integer.parseInt(getString(key));
    }
}
