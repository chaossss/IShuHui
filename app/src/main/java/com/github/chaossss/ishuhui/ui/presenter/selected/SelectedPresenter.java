package com.github.chaossss.ishuhui.ui.presenter.selected;

import com.github.chaossss.ishuhui.domain.model.AllBookModels;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by chaossss on 2016/2/2.
 */
public class SelectedPresenter implements OnSelectedComicGotListener {
    private WeakReference<View> view;
    private ISelectedInteractor selectedInteractor;

    public SelectedPresenter(View view) {
        this.view = new WeakReference<>(view);
        selectedInteractor = new SelectedInteractor();
    }

    public void getSelectedComic(){
        selectedInteractor.getSelectedComic(this);
    }

    @Override
    public void onSelectedComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> selectedComicList) {
        if(view.get() != null) view.get().onSelectedComicGotSuccess(selectedComicList);
    }

    @Override
    public void onSelectedComicGotFail(String errorInfo) {
        if(view.get() != null) view.get().onSelectedComicGotFail(errorInfo);
    }

    public interface View{
        void onSelectedComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> selectedComicList);
        void onSelectedComicGotFail(String errorInfo);
    }
}
