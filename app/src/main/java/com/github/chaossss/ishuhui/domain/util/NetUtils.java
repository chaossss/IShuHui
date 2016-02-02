package com.github.chaossss.ishuhui.domain.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.chaossss.ishuhui.ui.util.ToastUtils;

/**
 * Created by chaos on 2016/1/4.
 */
public class NetUtils {

    /**
     * 检验网络连接 并toast提示
     *
     * @return
     */
    public static  boolean isNetworkConnected(Context context)
    {
        ConnectivityManager con = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = con.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable())
        {
            ToastUtils.showToast(context.getApplicationContext(), "没有可用网络");
            return false;
        }
        return true;

    }
}
