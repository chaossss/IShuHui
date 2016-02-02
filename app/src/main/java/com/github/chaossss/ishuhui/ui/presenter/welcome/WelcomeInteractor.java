package com.github.chaossss.ishuhui.ui.presenter.welcome;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.BaseApplication;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.LoginModel;
import com.github.chaossss.ishuhui.domain.model.UserModel;
import com.github.chaossss.ishuhui.domain.util.LogUtils;
import com.github.chaossss.ishuhui.domain.util.SPUtils;

/**
 * Interactor that helps WelcomeActivity implement login function
 * Created by chaossss on 2016/2/2.
 */
public class WelcomeInteractor implements IWelcomeInteractor {

    public void login(final OnLoginFinishedListener listener, String email, final String psd) {
        AppDao.getInstance().userLogin(email, psd, new BaseCallbackListener<LoginModel>() {

            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(listener, AppConstant.LOGIN_RESULT + result);
            }

            @Override
            public void onSuccess(LoginModel result) {
                super.onSuccess(result);
                int status = Integer.valueOf(result.ErrCode);
                if (AppConstant.NET_RESPONSE_SUCCESS == status) {
                    BaseApplication.UserInfo = new UserModel();
                    BaseApplication.UserInfo.email = result.Return.Email;
                    BaseApplication.UserInfo.psd = psd;

                    SPUtils.saveObject(AppConstant.LOGIN_CACHE_NAME, BaseApplication.UserInfo);
                    listener.onLoginSuccess();
                } else {
                    listener.onLoginFail(result.ErrMsg);
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onLoginFail(AppConstant.NET_RESPONSE_ERROR);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }
}
