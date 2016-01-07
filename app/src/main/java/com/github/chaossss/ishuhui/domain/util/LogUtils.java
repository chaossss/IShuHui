package com.github.chaossss.ishuhui.domain.util;

import android.util.Log;

/**
 * Created by chaos on 2016/1/2.
 */
public class LogUtils {
    private static StringBuilder sb = new StringBuilder();

    private LogUtils() {
    }

    private static String generateTag(Object object){
        return object.getClass().getCanonicalName();
    }

    public static void logI(Object object, String... strs){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.length; i++){
            sb.append(strs[i]);
        }
        clear();

        Log.i(generateTag(object), sb.toString());
    }

    private static void clear(){
        if(sb != null && sb.length() != 0){
            sb.delete(0, sb.length());
        }
    }
}
