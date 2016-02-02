package com.github.chaossss.ishuhui.ui.presenter.comic_detail;

/**
 * Created by chaossss on 2016/2/2.
 */
public interface IComicDetailInteractor {
    void getComicDetail(OnComicDetailGotListener listener, String comicId, String pageIndex);
    void subscribeComic(OnComicSubscribedListener listener, String comicId, boolean isSubscribe);
}
