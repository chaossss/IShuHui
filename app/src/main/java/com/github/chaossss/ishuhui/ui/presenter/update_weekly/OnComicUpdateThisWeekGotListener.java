package com.github.chaossss.ishuhui.ui.presenter.update_weekly;

import com.github.chaossss.ishuhui.domain.model.AllBookModels;

import java.util.List;

/**
 * UpdateWeeklyFragment's abstract data operation
 * Created by chaossss on 2016/2/2.
 */
public interface OnComicUpdateThisWeekGotListener {
    void onComicUpdateThisWeekGotSuccess(List<AllBookModels.ReturnClazz.AllBook> comicUpdateThisWeekList);
    void onComicUpdateThisWeekGotFail(String errorInfo);
}
