package com.github.chaossss.ishuhui.ui.presenter.category;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AdvModel;
import com.github.chaossss.ishuhui.domain.util.LogUtils;

/**
 * Interactor that helps CategoryFragment implement get newest comic function
 * Created by chaossss on 2016/2/5.
 */
public class CategoryInteractor implements ICategoryInteractor {
    @Override
    public void getAdvData(final OnAdvDataGotListener listener) {
        AppDao.getInstance().getSlideData(new BaseCallbackListener<AdvModel>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, AppConstant.GET_CATEGORY_ADV_DATA_RESULT + result);
            }

            @Override
            public void onSuccess(AdvModel result) {
                super.onSuccess(result);
                if (result != null) {
                    listener.onAdvDataGotSuccess(result);
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onAdvDataGotFail(AppConstant.NET_RESPONSE_ERROR);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }
}
