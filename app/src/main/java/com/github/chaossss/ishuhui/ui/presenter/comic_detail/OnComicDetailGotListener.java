package com.github.chaossss.ishuhui.ui.presenter.comic_detail;

import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;

/**
 * Created by chaossss on 2016/2/2.
 */
public interface OnComicDetailGotListener {
    void onComicDetailGotSuccess(ComicDetailModel comicDetailModel);
    void onComicDetailGotFail(String errorInfo);
}
