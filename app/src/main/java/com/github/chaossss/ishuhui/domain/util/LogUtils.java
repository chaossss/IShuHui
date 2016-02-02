package com.github.chaossss.ishuhui.domain.util;

import android.util.Log;

/**
 * Utility class helps log sth easier
 * Created by chaos on 2016/1/2.
 */
public class LogUtils {
    private static LogUtils logUtils = new LogUtils();
    private static StringBuilder sb = new StringBuilder();

    private LogUtils() {
    }

    private String generateTag(Object object){
        return object.getClass().getSimpleName();
    }

    public static void logI(Object object, String... strs){
        StringBuilder sb = new StringBuilder();
        for(String str : strs){
            sb.append(str);
        }
        logUtils.clear();

        Log.i(logUtils.generateTag(object), sb.toString());
    }

    private void clear(){
        if(sb != null && sb.length() != 0){
            sb.delete(0, sb.length());
        }
    }
}
