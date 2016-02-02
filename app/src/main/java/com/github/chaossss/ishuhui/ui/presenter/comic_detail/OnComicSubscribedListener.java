package com.github.chaossss.ishuhui.ui.presenter.comic_detail;

/**
 * ComicDetailActivity's abstract subscribe operation
 * Created by chaossss on 2016/2/2.
 */
public interface OnComicSubscribedListener {
    void onComicSubscribedSuccess(boolean isSubscribed);
    void onComicSubscribedFail(String errorInfo);
}
