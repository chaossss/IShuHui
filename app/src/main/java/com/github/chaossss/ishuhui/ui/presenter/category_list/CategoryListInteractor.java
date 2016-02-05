package com.github.chaossss.ishuhui.ui.presenter.category_list;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.CategoryModel;
import com.github.chaossss.ishuhui.domain.util.LogUtils;

/**
 * Interactor that helps CategoryListFragment implement get newest comic function
 * Created by chaossss on 2016/2/5.
 */
public class CategoryListInteractor implements ICategoryListInteractor {
    @Override
    public void getCategoryData(final OnCategoryGotListener listener, String type, String num, String pageIndex) {
        AppDao.getInstance().getCategoryData(String.valueOf(type), num, pageIndex, new BaseCallbackListener<CategoryModel>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, AppConstant.GET_CATEGORY_RESULT + result);
            }

            @Override
            public void onSuccess(CategoryModel result) {
                super.onSuccess(result);
                if (result != null) {
                    listener.onCategoryGotSuccess(result);
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                listener.onCategoryGotFail(AppConstant.NET_RESPONSE_ERROR);
                LogUtils.logI(listener, AppConstant.EXCEPTION + e.toString());
            }
        });
    }
}
