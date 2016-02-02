package com.github.chaossss.ishuhui.ui.presenter.welcome;

/**
 * Created by chaos on 2016/2/2.
 */
public interface IWelcomeInteractor {
    void login(OnLoginFinishedListener listener, String email, String psd);
}
