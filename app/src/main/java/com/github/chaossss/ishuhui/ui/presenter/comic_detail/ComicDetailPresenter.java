package com.github.chaossss.ishuhui.ui.presenter.comic_detail;

import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;

import java.lang.ref.WeakReference;

/**
 * Presenter helps ComicDetailActivity implement it's get comic detail & subscribe function
 * Created by chaossss on 2016/2/2.
 */
public class ComicDetailPresenter implements OnComicDetailGotListener, OnComicSubscribedListener {
    private WeakReference<View> view;
    private IComicDetailInteractor comicDetailInteractor;

    public ComicDetailPresenter(View view) {
        this.view = new WeakReference<>(view);
        comicDetailInteractor = new ComicDetailInteractor();
    }

    public void getComicDetail(String comicId, String pageIndex){
        comicDetailInteractor.getComicDetail(this, comicId, pageIndex);
    }

    public void subscribeComic(String comicId, boolean isSubscribe){
        comicDetailInteractor.subscribeComic(this, comicId, isSubscribe);
    }

    @Override
    public void onComicDetailGotSuccess(ComicDetailModel comicDetailModel) {
        if(view.get() != null) view.get().onComicDetailGotSuccess(comicDetailModel);
    }

    @Override
    public void onComicDetailGotFail(String errorInfo) {
        if(view.get() != null) view.get().onComicDetailGotFail(errorInfo);
    }

    @Override
    public void onComicSubscribedSuccess(boolean isSubscribed) {
        if(view.get() != null) view.get().onComicSubscribedSuccess(isSubscribed);
    }

    @Override
    public void onComicSubscribedFail(String errorInfo) {
        if(view.get() != null) view.get().onComicSubscribedFail(errorInfo);
    }

    public interface View{
        void onComicDetailGotSuccess(ComicDetailModel comicDetailModel);
        void onComicDetailGotFail(String errorInfo);

        void onComicSubscribedSuccess(boolean isSubscribed);
        void onComicSubscribedFail(String errorInfo);
    }
}
