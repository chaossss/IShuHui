package com.github.chaossss.ishuhui.domain.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.chaossss.ishuhui.domain.BaseApplication;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by chaossss on 2016/1/5.
 */
public class SPUtils {
    private static final Gson gson = new Gson();
    private static final String EMPTY_STR = "";
    private static final String APP_ID = "IShuHui";
    private static final Context context = BaseApplication.baseApplication;

    private SPUtils(){
        throw new UnsupportedOperationException("Instantiation is forbidden!");
    }

    /**
     * Cache obj mapped to cacheName
     */
    public static void saveObject(String cacheName, Object obj){
        SharedPreferences.Editor e = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE).edit();
        String json =  gson.toJson(obj);
        e.putString(cacheName, json);
        e.apply();
    }

    /**
     * Get cache by cacheName
     */
    public static String getObject(String cacheName){
        return getObject(cacheName,String.class,null);
    }

    /**
     * Get cache by cacheName
     * @param classes	obj's type
     */
    public static <T> T getObject(String cacheName, Class<T> classes){
        return getObject(cacheName, classes, null);
    }

    /**
     * Get cache by cacheName
     * @param classes	obj's type
     * @param defaultValue	default params
     */
    public static <T> T getObject(String cacheName, Class<T> classes, T defaultValue){
        SharedPreferences s = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        String jsonString = s.getString(cacheName, EMPTY_STR);
        if(StringUtils.isValid(jsonString)){
            try {
                T o = gson.fromJson(jsonString, classes);
                return o;
            }catch (Exception e){
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Get cache by cacheName
     */
    public static <T> T getObject(String cacheName,Type type){
        return getObject(cacheName,type,null);
    }

    /**
     * Get cache by cacheName
     * @param type	obj's type
     * @param defaultValue	default params
     */
    public static <T> T getObject(String cacheName,Type type,T defaultValue){
        SharedPreferences s = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        String jsonString = s.getString(cacheName, EMPTY_STR);
        if(!StringUtils.isValid(jsonString)){
            try {
                T o = gson.fromJson(jsonString, type);
                return o;
            }catch (Exception e){
                e.printStackTrace();
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /**
     * Delete cache
     */
    public static void delObject(String cacheName){
        SharedPreferences s = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        s.edit().remove(cacheName).apply();
    }

    /**
     * Clear all cache
     */
    public static void clearAll()
    {
        SharedPreferences sp = context.getSharedPreferences(APP_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear().commit();
    }
}
