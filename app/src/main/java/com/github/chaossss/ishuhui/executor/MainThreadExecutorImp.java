package com.github.chaossss.ishuhui.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by chaos on 2016/1/2.
 */
public class MainThreadExecutorImp implements MainThreadExecutor {
    private Handler handler;

    public MainThreadExecutorImp() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
