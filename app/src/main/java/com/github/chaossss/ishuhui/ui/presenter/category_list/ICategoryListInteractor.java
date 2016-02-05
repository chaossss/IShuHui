package com.github.chaossss.ishuhui.ui.presenter.category_list;

/**
 * CategoryListInteractor's abstract implementation
 * Created by chaossss on 2016/2/5.
 */
public interface ICategoryListInteractor {
    void getCategoryData(OnCategoryGotListener listener, String type, String num, String pageIndex);
}
