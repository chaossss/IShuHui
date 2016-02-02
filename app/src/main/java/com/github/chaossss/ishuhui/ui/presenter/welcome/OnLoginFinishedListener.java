package com.github.chaossss.ishuhui.ui.presenter.welcome;

/**
 * WelcomeActivity's abstract data operation
 * Created by chaossss on 2016/2/2.
 */
public interface OnLoginFinishedListener {
    void onLoginSuccess();
    void onLoginFail(String errorInfo);
}
