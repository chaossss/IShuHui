package com.github.chaossss.ishuhui.ui.presenter.comic_detail;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.BaseApplication;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;
import com.github.chaossss.ishuhui.domain.model.SubscribeModel;
import com.github.chaossss.ishuhui.domain.util.LogUtils;
import com.github.chaossss.ishuhui.domain.util.SPUtils;

/**
 * Created by chaossss on 2016/2/2.
 */
public class ComicDetailInteractor implements IComicDetailInteractor {
    @Override
    public void getComicDetail(final OnComicDetailGotListener listener, String comicId, String pageIndex) {
        AppDao.getInstance().getBookComicData(comicId, String.valueOf(pageIndex), new BaseCallbackListener<ComicDetailModel>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, AppConstant.COMIC_DETAIL_RESULT + result);
            }

            @Override
            public void onSuccess(ComicDetailModel result) {
                super.onSuccess(result);
                listener.onComicDetailGotSuccess(result);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onComicDetailGotFail(AppConstant.NET_RESPONSE_ERROR);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }

    @Override
    public void subscribeComic(final OnComicSubscribedListener listener, final String comicId, final boolean isSubscribe) {
        AppDao.getInstance().subscribeBook(comicId, isSubscribe, new BaseCallbackListener<SubscribeModel>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, AppConstant.SUBSCRIBE_RESULT + result);
            }

            @Override
            public void onSuccess(SubscribeModel result) {
                super.onSuccess(result);
                if (!isSubscribe) {
                    SPUtils.saveObject(BaseApplication.UserInfo.email + "id" + comicId, comicId);
                    listener.onComicSubscribedSuccess(true);
                } else {
                    SPUtils.saveObject(BaseApplication.UserInfo.email + "id" + comicId, "-2");
                    listener.onComicSubscribedSuccess(false);
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onComicSubscribedFail(AppConstant.SUBSCRIBE_FAIL);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }
}
