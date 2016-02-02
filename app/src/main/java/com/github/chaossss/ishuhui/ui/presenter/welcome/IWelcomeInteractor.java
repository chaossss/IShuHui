package com.github.chaossss.ishuhui.ui.presenter.welcome;

/**
 * WelcomeInteractor's abstract implementation
 * Created by chaossss on 2016/2/2.
 */
public interface IWelcomeInteractor {
    void login(OnLoginFinishedListener listener, String email, String psd);
}
