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
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chaos on 2016/1/4.
 */
public class NewestFragment extends Fragment implements PullRefreshLayout.OnRefreshListener {
    @Bind(R.id.newest_newest_list)
    RecyclerView recyclerView;
    @Bind(R.id.newest_refresh_layout)
    PullRefreshLayout pullRefreshLayout;

    private NewestAdapter newestAdapter;
    private List<AllBookModels.ReturnClazz.AllBook> newestList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newestList = new ArrayList<>();
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
        getBookData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void getBookData() {
        AppDao.getInstance().getAllBook(new BaseCallbackListener<AllBookModels>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                LogUtils.logI(this, "onStringResult" + result);
            }

            @Override
            public void onSuccess(AllBookModels result) {
                super.onSuccess(result);
                newestList =  result.Return.List;

                if (newestAdapter == null) {
                    newestAdapter = new NewestAdapter(getContext(), newestList);
                    recyclerView.setAdapter(newestAdapter);
                } else {
                    newestAdapter.updateData(newestList);
                }

                pullRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                ToastUtils.showToast(getActivity(), e.toString());
                pullRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        getBookData();
    }
}
