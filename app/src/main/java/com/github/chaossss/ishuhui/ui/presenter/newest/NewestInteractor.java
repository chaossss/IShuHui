package com.github.chaossss.ishuhui.ui.presenter.newest;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.domain.util.LogUtils;

/**
 * Interactor that helps NewestFragment implement login function
 * Created by chaossss on 2016/2/2.
 */
public class NewestInteractor implements INewestInteractor {
    @Override
    public void getNewestComic(final OnNewestComicGotListener listener) {
        AppDao.getInstance().getAllBook(new BaseCallbackListener<AllBookModels>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, AppConstant.GET_NEWEST_COMIC_RESULT + result);
            }

            @Override
            public void onSuccess(AllBookModels result) {
                super.onSuccess(result);
                listener.onNewestComicGotSuccess(result.Return.List);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onNewestComicGotFail(AppConstant.NET_RESPONSE_ERROR);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }
}
