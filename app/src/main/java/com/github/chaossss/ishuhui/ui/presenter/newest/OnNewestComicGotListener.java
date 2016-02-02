package com.github.chaossss.ishuhui.ui.presenter.newest;

import com.github.chaossss.ishuhui.domain.model.AllBookModels;

import java.util.List;

/**
 * NewestFragment's abstract data operation
 * Created by chaossss on 2016/2/2.
 */
public interface OnNewestComicGotListener {
    void onNewestComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> newestComicList);
    void onNewestComicGotFail(String errorInfo);
}
