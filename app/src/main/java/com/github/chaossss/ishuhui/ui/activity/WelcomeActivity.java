package com.github.chaossss.ishuhui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.model.UserModel;
import com.github.chaossss.ishuhui.domain.util.SPUtils;
import com.github.chaossss.ishuhui.ui.presenter.welcome.WelcomePresenter;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Welcome page that shows IShuHui App's base info
 * Created by chaossss on 2016/1/2.
 */
public class WelcomeActivity extends AppCompatActivity implements Animation.AnimationListener, WelcomePresenter.View {
    @Bind(R.id.welcome_splash)
    ImageView splash;
    private WelcomePresenter welcomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        animation.setAnimationListener(this);
        splash.setAnimation(animation);

        welcomePresenter = new WelcomePresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void handLogin(){
        UserModel userInfo = SPUtils.getObject(AppConstant.LOGIN_CACHE_NAME, UserModel.class);

        if(userInfo != null){
            login(userInfo.email, userInfo.psd);
        } else {
            login(AppConstant.LOGIN_EMAIL, AppConstant.LOGIN_PSD);
        }
    }

    private void login(String email, String psd){
        welcomePresenter.login(email, psd);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        handLogin();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onLoginSuccess() {
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onLoginFail(String errorInfo) {
        ToastUtils.showToast(WelcomeActivity.this, errorInfo);
    }
}
