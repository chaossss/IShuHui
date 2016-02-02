package com.github.chaossss.ishuhui.ui.presenter.update_weekly;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.domain.util.LogUtils;

/**
 * Created by chaossss on 2016/2/2.
 */
public class UpdateWeeklyInteractor implements IUpdateWeeklyInteractor {
    @Override
    public void getComicUpdatedThisWeek(final OnComicUpdateThisWeekGotListener listener) {
        AppDao.getInstance().getWeekBook("7", "0", new BaseCallbackListener<AllBookModels>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, AppConstant.GET_COMIC_UPDATED_THIS_WEEK_RESULT + result);
            }

            @Override
            public void onSuccess(AllBookModels result) {
                super.onSuccess(result);
                listener.onComicUpdateThisWeekGotSuccess(result.Return.List);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onComicUpdateThisWeekGotFail(AppConstant.NET_RESPONSE_ERROR);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }
}
