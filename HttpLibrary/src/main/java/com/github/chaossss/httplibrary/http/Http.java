package com.github.chaossss.httplibrary.http;

import com.github.chaossss.httplibrary.listener.CallBackListener;

import java.util.Map;

/**
 * Created by chaos on 2016/1/1.
 */
public class Http{
    public static void get(String url,CallBackListener<?> listener)
    {
        BaseHttp.getInstance().baseGet(url, listener);
    }

    public static void post(String url,Map<String,String>params,CallBackListener<?> listener)
    {
        BaseHttp.getInstance().basePost(url, params, listener);
    }

    public static void postNotParams(String url,CallBackListener<?> listener)
    {
        BaseHttp.getInstance().basePost(url, null, listener);
    }

    public static void download(String url,String savePath,CallBackListener<?> listener)
    {
        BaseHttp.getInstance().baseDownload(url, savePath, listener);
    }

    public static void cancel(String url)
    {
        BaseHttp.getInstance().getOkHttpClient().cancel(url);
    }
}
