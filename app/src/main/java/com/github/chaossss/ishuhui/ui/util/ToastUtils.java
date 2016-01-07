package com.github.chaossss.ishuhui.ui.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by chaos on 2016/1/4.
 */
public class ToastUtils {
    public static void showToast(Context context, String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }
}
