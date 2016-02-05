package com.github.chaossss.ishuhui.ui.presenter.category;

import com.github.chaossss.ishuhui.domain.model.AdvModel;

import java.lang.ref.WeakReference;

/**
 * Presenter helps CategoryFragment implement it's get adv data function
 * Created by chaossss on 2016/2/5.
 */
public class CategoryPresenter implements OnAdvDataGotListener {
    private WeakReference<View> view;
    private ICategoryInteractor categoryInteractor;

    public CategoryPresenter(View view) {
        this.view = new WeakReference<>(view);
        categoryInteractor = new CategoryInteractor();
    }

    public void getAdvData() {
        categoryInteractor.getAdvData(this);
    }

    @Override
    public void onAdvDataGotSuccess(AdvModel advModel){
        if(view != null) view.get().onAdvDataGotSuccess(advModel);
    }

    @Override
    public void onAdvDataGotFail(String errorInfo){
        if(view != null) view.get().onAdvDataGotFail(errorInfo);
    }

    public interface View{
        void onAdvDataGotSuccess(AdvModel advModel);
        void onAdvDataGotFail(String errorInfo);
    }
}
