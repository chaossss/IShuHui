package com.github.chaossss.ishuhui.ui.presenter.category_list;

import com.github.chaossss.ishuhui.domain.model.CategoryModel;

/**
 * CategoryFragment's abstract data operation
 * Created by chaossss on 2016/2/5.
 */
public interface OnCategoryGotListener {
    void onCategoryGotSuccess(CategoryModel categoryModel);
    void onCategoryGotFail(String errorInfo);
}
