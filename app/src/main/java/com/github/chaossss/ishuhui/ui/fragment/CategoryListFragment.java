package com.github.chaossss.ishuhui.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.CategoryModel;
import com.github.chaossss.ishuhui.ui.adapter.CategoryListAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chaos on 2016/1/9.
 */
public class CategoryListFragment extends Fragment {
    public static final int CATEGORY_FIRST = 0;
    public static final int CATEGORY_SECOND = 1;
    public static final int CATEGORY_THIRD = 2;
    public static final int CATEGORY_NUMS = 3;

    public static final String DATA_NUMS = "30";
    public static final String PAGE_INDEX = "0";
    public static final String CATEGORY_FIRST_TITLE = "热血";
    public static final String CATEGORY_SECOND_TITLE = "国产";
    public static final String CATEGORY_THIRD_TITLE = "鼠绘";

    private int type;
    private List<CategoryModel.ReturnEntity.ListEntity> categoryListDatas;

    @Bind(R.id.category_list_list)
    RecyclerView categoryList;
    private CategoryListAdapter categoryListAdapter;

    public CategoryListFragment() {
    }

    public static CategoryListFragment newInstance(int type){
        CategoryListFragment categoryListFragment = new CategoryListFragment();
        if(type == CATEGORY_FIRST){
            categoryListFragment.type = type;
        } else {
            categoryListFragment.type = type + 1;
        }
        return categoryListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_list, container, false);
        ButterKnife.bind(this, v);

        categoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getCategoryData();
        categoryListAdapter = new CategoryListAdapter(getContext(), categoryListDatas);
        categoryList.setAdapter(categoryListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void getCategoryData(){
        AppDao.getInstance().getCategoryData(String.valueOf(type), DATA_NUMS, PAGE_INDEX, new BaseCallbackListener<CategoryModel>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
            }

            @Override
            public void onSuccess(CategoryModel result) {
                super.onSuccess(result);
                if (result != null) {
                    categoryListDatas = result.Return.List;
                    if(categoryListDatas.size() == 0){
                        getCategoryData();
                    } else {
                        categoryListAdapter.updateDatas(categoryListDatas);
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
            }
        });
    }
}
