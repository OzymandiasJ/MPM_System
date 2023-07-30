package com.ozy.myssm.utils;

public class StringUtils {
    public static boolean isEmpty(String str){
        return str==null||"".equals(str);
    }
    public static boolean isNotEmpty(String str){
        return !(str==null||"".equals(str));
    }

}
