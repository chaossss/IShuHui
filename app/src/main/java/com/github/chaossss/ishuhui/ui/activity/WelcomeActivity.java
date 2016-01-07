package com.github.chaossss.ishuhui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.BaseApplication;
import com.github.chaossss.ishuhui.domain.constants.LoginConstant;
import com.github.chaossss.ishuhui.domain.constants.NetResponseConstant;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.LoginModel;
import com.github.chaossss.ishuhui.domain.model.UserModel;
import com.github.chaossss.ishuhui.domain.util.LogUtils;
import com.github.chaossss.ishuhui.domain.util.SPUtils;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

/**
 * Welcome page that shows IShuHui App's base info
 * Created by chaos on 2016/1/2.
 */
public class WelcomeActivity extends AppCompatActivity implements Animation.AnimationListener {
    private ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        splash = (ImageView) findViewById(R.id.welcome_splash);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splash);
        animation.setAnimationListener(this);
        splash.setAnimation(animation);
    }

    private void handLogin(){
        UserModel userInfo = SPUtils.getObject("key_login", UserModel.class);

        if(userInfo != null){
            login(userInfo);
        } else {
            login(LoginConstant.EMAIL, LoginConstant.PSD);
        }
    }

    private void login(UserModel userInfo){
        login(userInfo.email, userInfo.psd);
    }

    private void login(String email, final String psd){
        AppDao.getInstance().userLogin(email, psd, new BaseCallbackListener<LoginModel>() {

            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, "login result----->" + result);
            }

            @Override
            public void onSuccess(LoginModel result) {
                super.onSuccess(result);
                int status = Integer.valueOf(result.ErrCode);
                if (NetResponseConstant.success == status) {
                    BaseApplication.UserInfo = new UserModel();
                    BaseApplication.UserInfo.email = result.Return.Email;
                    BaseApplication.UserInfo.psd = psd;

                    SPUtils.saveObject("key_login", BaseApplication.UserInfo);
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    WelcomeActivity.this.finish();

                } else {
                    ToastUtils.showToast(WelcomeActivity.this, result.ErrMsg);
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                LogUtils.logI(WelcomeActivity.this, "Exception----->" + e.toString());
                ToastUtils.showToast(WelcomeActivity.this, NetResponseConstant.NET_ERROR);
            }
        });
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
}
