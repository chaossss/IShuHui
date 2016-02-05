package com.github.chaossss.ishuhui.ui.presenter.category_list;

import com.github.chaossss.ishuhui.domain.model.CategoryModel;

import java.lang.ref.WeakReference;

/**
 * Presenter helps CategoryListFragment implement it's get category comic function
 * Created by chaossss on 2016/2/5.
 */
public class CategoryListPresenter implements OnCategoryGotListener {
    private WeakReference<View> view;
    private ICategoryListInteractor categoryListInteractor;

    public CategoryListPresenter(View view) {
        this.view = new WeakReference<>(view);
        categoryListInteractor = new CategoryListInteractor();
    }

    public void getCategoryData(String type, String num, String pageIndex){
        categoryListInteractor.getCategoryData(this, type, num, pageIndex);
    }

    @Override
    public void onCategoryGotSuccess(CategoryModel categoryModel) {
        if(view.get() != null) view.get().onCategoryGotSuccess(categoryModel);
    }

    @Override
    public void onCategoryGotFail(String errorInfo) {
        if(view.get() != null) view.get().onCategoryGotFail(errorInfo);
    }

    public interface View{
        void onCategoryGotSuccess(CategoryModel categoryModel);
        void onCategoryGotFail(String errorInfo);
    }
}
