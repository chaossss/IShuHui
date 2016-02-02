package com.github.chaossss.ishuhui.ui.presenter.update_weekly;

import com.github.chaossss.ishuhui.domain.model.AllBookModels;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Presenter helps UpdateWeeklyFragment implement it's get comic updated this week function
 * Created by chaossss on 2016/2/2.
 */
public class UpdateWeeklyPresenter implements OnComicUpdateThisWeekGotListener {
    private WeakReference<View> view;
    private IUpdateWeeklyInteractor updateWeeklyInteractor;

    public UpdateWeeklyPresenter(View view) {
        this.view = new WeakReference<>(view);
        updateWeeklyInteractor = new UpdateWeeklyInteractor();
    }

    public void getComicUpdatedThisWeek(){
        updateWeeklyInteractor.getComicUpdatedThisWeek(this);
    }

    @Override
    public void onComicUpdateThisWeekGotSuccess(List<AllBookModels.ReturnClazz.AllBook> comicUpdateThisWeekList) {
        if(view.get() != null) view.get().onComicUpdateThisWeekGotSuccess(comicUpdateThisWeekList);
    }

    @Override
    public void onComicUpdateThisWeekGotFail(String errorInfo) {
        if(view.get() != null) view.get().onComicUpdateThisWeekGotFail(errorInfo);
    }

    public interface View{
        void onComicUpdateThisWeekGotSuccess(List<AllBookModels.ReturnClazz.AllBook> comicUpdateThisWeekList);
        void onComicUpdateThisWeekGotFail(String errorInfo);
    }
}
