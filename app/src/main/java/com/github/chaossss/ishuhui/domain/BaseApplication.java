package com.github.chaossss.ishuhui.domain;

import android.app.Application;

import com.github.chaossss.ishuhui.domain.model.UserModel;

/**
 * Created by chaossss on 2016/1/5.
 */
public class BaseApplication extends Application {
    public static UserModel UserInfo;
    public static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }
}
