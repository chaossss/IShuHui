package com.github.chaossss.ishuhui.ui.presenter.selected;

import com.github.chaossss.ishuhui.domain.model.AllBookModels;

import java.util.List;

/**
 * Created by chaossss on 2016/2/2.
 */
public interface OnSelectedComicGotListener {
    void onSelectedComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> selectedComicList);
    void onSelectedComicGotFail(String errorInfo);
}
