package com.github.chaossss.httplibrary.http;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chaossss.httplibrary.listener.CallBackListener;
import com.github.chaossss.httplibrary.util.GenericsUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

/**
 * Class provides base http function
 * Created by chaos on 2016/1/1.
 */
public class BaseHttp<T>{
    private static int KB = 1024;
    private static final String TAG = "BaseHttp";

    private Gson mGson;
    private Handler mHandler;
    private OkHttpClient mOkHttpClient;
    private volatile static BaseHttp mBaseHttp;

    private BaseHttp()
    {
        init();
    }

    public static BaseHttp getInstance()
    {
        if(mBaseHttp == null)
        {
            synchronized (BaseHttp.class){
                if(mBaseHttp == null){
                    mBaseHttp = new BaseHttp();
                }
            }
        }
        return  mBaseHttp;
    }

    protected void init(){
        mGson = new Gson();
        mOkHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
        //cookie enabled
        mOkHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));
    }

    public void baseGet(String url,CallBackListener<T> listener)
    {
        Request request = getBaseRequest(url);
        doRequest(request, listener);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public void basePost(String url, Map<String, String> params, CallBackListener<T> listener)
    {
        if (params == null) {
            baseGet(url,listener);return;
        }
        FormEncodingBuilder builder = new FormEncodingBuilder();
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .tag(url)
                .build();
        doRequest(request, listener);
    }

    public void baseDownload(final String url, final String savePath, final CallBackListener<T> listener)
    {
        Request request = getBaseRequest(url);
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                if (isListenerNotNull(listener)) listener.onError(e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                InputStream is = response.body().byteStream();
                startDownload(url, savePath, is, listener);
            }
        });
    }

    public void baseDisplayImage(T t, ImageView imageView, String imgUrl){
        Glide.with((Fragment)t).load(imgUrl).into(imageView);
    }

    public void baseDisplayImage(T t,ImageView imageView,String imgUrl,int placeholderId,int errorId)
    {
        if(t instanceof Activity) {
            Glide.with((Activity) t).load(imgUrl).placeholder(placeholderId).error(errorId).into(imageView);
        }else if(t instanceof Fragment)
        {
            Glide.with((Fragment) t).load(imgUrl).placeholder(placeholderId).error(errorId).into(imageView);
        }else if(t instanceof Context)
        {
            Glide.with((Context) t).load(imgUrl).placeholder(placeholderId).error(errorId).into(imageView);
        }else
        {
            throw new RuntimeException("the t must be context or activity or fragment");
        }
    }

    public void startDownload(String urlPath,String savePath,InputStream is,CallBackListener<T> listener) {
        FileOutputStream fos = null;
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Log.v(TAG, "savePath-------->" + savePath);

                File file = new File(savePath,getFileNameByUrl(urlPath));

                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }

                int len;
                int progress = 0;
                byte buf[] = new byte[2 * KB];
                fos = new FileOutputStream(file);

                while ((len = is.read(buf)) != -1)
                {
                    progress += len;
                    if(isListenerNotNull(listener)) listener.onDownloadProgress(progress);
                    fos.write(buf, 0, len);
                }

                if(isListenerNotNull(listener)){
                    listener.onDownloadFinish(file.getAbsolutePath());
                }

                fos.flush();
            } else {
                Log.i(TAG, "didn't find sdcard or sdcard no permission");
            }
        } catch (MalformedURLException e) {
            if(isListenerNotNull(listener)){
                listener.onError(e);
            }
        } catch (IOException e) {
            if(isListenerNotNull(listener)){
                listener.onError(e);
            }
        }finally
        {
            try
            {
                if (is != null){
                    is.close();
                }

                if (fos != null){
                    fos.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get url's name
     */
    public static String getFileNameByUrl(String strUrl) {
        if (!TextUtils.isEmpty(strUrl)) {
            try {
                return strUrl.substring(strUrl.lastIndexOf("/") + 1, strUrl.length());
            } catch (IndexOutOfBoundsException e) {
                return strUrl;
            }
        } else {
            return strUrl;
        }
    }

    public Request getBaseRequest(String url)
    {
        return  new Request.Builder().url(url).tag(url).build();
    }

    public void doRequest(Request request, final CallBackListener<T> listener)
    {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError(e);
                    }
                });

            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                Log.i(TAG, "结果：" + result);
                if(isListenerNotNull(listener)) listener.onStringResult(result);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Class<T> clazz = GenericsUtils.getSuperClassGenricType(listener.getClass());
                            if (clazz == String.class) {        //字符串
                                if(isListenerNotNull(listener))
                                    listener.onSuccess((T) result);
                            } else {//Object
                                if(isListenerNotNull(listener))
                                    listener.onSuccess(mGson.fromJson(result, clazz));
                            }
                        } catch (Exception e) {
                            Log.i(TAG, "doRequest------->" + "onResponse------->Error", e);
                            if(isListenerNotNull(listener))
                                listener.onError(e);
                        }
                    }
                });
            }
        });
    }

    public Boolean isListenerNotNull(CallBackListener<T> listener)
    {
        return listener != null;
    }
}
