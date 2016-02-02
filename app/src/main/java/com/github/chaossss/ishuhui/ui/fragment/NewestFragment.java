package com.github.chaossss.ishuhui.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.domain.util.LogUtils;
import com.github.chaossss.ishuhui.ui.adapter.NewestAdapter;
import com.github.chaossss.ishuhui.ui.presenter.newest.NewestPresenter;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chaos on 2016/1/4.
 */
public class NewestFragment extends Fragment implements NewestPresenter.View, PullRefreshLayout.OnRefreshListener {
    @Bind(R.id.newest_newest_list)
    RecyclerView recyclerView;
    @Bind(R.id.newest_refresh_layout)
    PullRefreshLayout pullRefreshLayout;

    private NewestPresenter presenter;
    private NewestAdapter newestAdapter;
    private List<AllBookModels.ReturnClazz.AllBook> newestList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newestList = new ArrayList<>();
        presenter = new NewestPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newest, container, false);
        ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pullRefreshLayout.setOnRefreshListener(this);
        presenter.getNewestComic();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        presenter.getNewestComic();
    }

    @Override
    public void onNewestComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> newestComicList) {
        newestList = newestComicList;

        if (newestAdapter == null) {
            newestAdapter = new NewestAdapter(getContext(), newestList);
            recyclerView.setAdapter(newestAdapter);
        } else {
            newestAdapter.updateData(newestList);
        }

        pullRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNewestComicGotFail(String errorInfo) {
        pullRefreshLayout.setRefreshing(false);
    }
}
