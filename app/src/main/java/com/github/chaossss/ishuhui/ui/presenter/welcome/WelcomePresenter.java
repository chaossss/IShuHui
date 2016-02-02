package com.github.chaossss.ishuhui.ui.presenter.welcome;

import java.lang.ref.WeakReference;

/**
 * Presenter helps WelcomeActivity's finish it's login function
 * Created by chaos on 2016/2/2.
 */
public class WelcomePresenter implements OnLoginFinishedListener {
    private WeakReference<View> view;
    private IWelcomeInteractor welcomeInteractor;

    public WelcomePresenter(View view) {
        this.view = new WeakReference<>(view);
        welcomeInteractor = new WelcomeInteractor();
    }

    public void login(String email, String psd){
        welcomeInteractor.login(this, email, psd);
    }

    public void onLoginSuccess(){
        if(view.get() != null) view.get().onLoginSuccess();
    }

    @Override
    public void onLoginFail(String errorInfo) {
        if(view.get() != null) view.get().onLoginFail(errorInfo);
    }

    public interface View{
        void onLoginSuccess();
        void onLoginFail(String errorInfo);
    }
}
