package com.github.chaossss.ishuhui.domain.util;

/**
 * Created by chaos on 2016/1/2.
 */
public class StringUtils {
    public static final String EMPTY_STR = "";
    private static StringBuilder sb = new StringBuilder();
    private static StringUtils stringUtils = new StringUtils();

    private StringUtils() {
    }

    public static boolean isValid(String str){
        return str != null && !str.equals("");
    }

    public static String generateStr(String... strs){
        for(int i = 0; i < strs.length; i++){
            sb.append(strs[i]);
        }
        String result = sb.toString();
        stringUtils.clear();

        return result;
    }

    private void clear(){
        if(sb.length() != 0){
            sb.delete(0, sb.length());
        }
    }
}
