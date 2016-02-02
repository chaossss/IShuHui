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
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.ui.adapter.NewestAdapter;
import com.github.chaossss.ishuhui.ui.presenter.update_weekly.UpdateWeeklyPresenter;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UpdateWeeklyFragment extends Fragment implements UpdateWeeklyPresenter.View, PullRefreshLayout.OnRefreshListener {
    @Bind(R.id.newest_newest_list)
    RecyclerView recyclerView;
    @Bind(R.id.newest_refresh_layout)
    PullRefreshLayout pullRefreshLayout;

    private UpdateWeeklyPresenter presenter;
    private NewestAdapter updateWeeklyAdapter;
    private List<AllBookModels.ReturnClazz.AllBook> updateWeeklyList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateWeeklyList = new ArrayList<>();
        presenter = new UpdateWeeklyPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newest, container, false);
        ButterKnife.bind(this, v);

        pullRefreshLayout = (PullRefreshLayout) v.findViewById(R.id.newest_refresh_layout);

        recyclerView = (RecyclerView) v.findViewById(R.id.newest_newest_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pullRefreshLayout.setOnRefreshListener(this);
        presenter.getComicUpdatedThisWeek();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        presenter.getComicUpdatedThisWeek();
    }

    @Override
    public void onComicUpdateThisWeekGotSuccess(List<AllBookModels.ReturnClazz.AllBook> comicUpdateThisWeekList) {
        updateWeeklyList = comicUpdateThisWeekList;

        if (updateWeeklyAdapter == null) {
            updateWeeklyAdapter = new NewestAdapter(getContext(), updateWeeklyList);
            recyclerView.setAdapter(updateWeeklyAdapter);
        } else {
            updateWeeklyAdapter.updateData(updateWeeklyList);
        }

        pullRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onComicUpdateThisWeekGotFail(String errorInfo) {
        pullRefreshLayout.setRefreshing(false);
        ToastUtils.showToast(getContext(), errorInfo);
    }
}
