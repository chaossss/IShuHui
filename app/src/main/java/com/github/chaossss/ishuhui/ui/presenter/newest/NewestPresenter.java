package com.github.chaossss.ishuhui.ui.presenter.newest;

import com.github.chaossss.ishuhui.domain.model.AllBookModels;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter helps NewestFragment implement it's get newest comic function
 * Created by chaossss on 2016/2/2.
 */
public class NewestPresenter implements OnNewestComicGotListener {
    private WeakReference<View> view;
    private INewestInteractor newestInteractor;

    public NewestPresenter(View view) {
        this.view = new WeakReference<>(view);
        newestInteractor = new NewestInteractor();
    }

    public void getNewestComic(){
        newestInteractor.getNewestComic(this);
    }

    @Override
    public void onNewestComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> newestComicList) {
        if(view.get() != null) view.get().onNewestComicGotSuccess(newestComicList);
    }

    @Override
    public void onNewestComicGotFail(String errorInfo) {
        if(view.get() != null) view.get().onNewestComicGotFail(errorInfo);
    }

    public interface View{
        void onNewestComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> newestComicList);
        void onNewestComicGotFail(String errorInfo);
    }
}
