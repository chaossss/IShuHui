package com.github.chaossss.ishuhui.ui.presenter.category;

import com.github.chaossss.ishuhui.domain.model.AdvModel;

/**
 * CategoryListFragment's abstract data operation
 * Created by chaossss on 2016/2/5.
 */
public interface OnAdvDataGotListener {
    void onAdvDataGotSuccess(AdvModel advModel);
    void onAdvDataGotFail(String errorInfo);
}
