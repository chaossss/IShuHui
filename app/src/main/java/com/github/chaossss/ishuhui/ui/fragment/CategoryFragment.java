package com.github.chaossss.ishuhui.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;
import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AdvModel;
import com.github.chaossss.ishuhui.domain.util.LogUtils;
import com.github.chaossss.ishuhui.ui.adapter.AdvPagerAdapter;
import com.github.chaossss.ishuhui.ui.adapter.ComicPagerAdapter;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by chaos on 2016/1/8.
 */
public class CategoryFragment extends Fragment implements PullRefreshLayout.OnRefreshListener {
    private TabLayout tabLayout;
    private ViewPager advViewPager;
    private ViewPager comicViewPager;
    private CircleIndicator circleIndicator;
    private PullRefreshLayout pullRefreshLayout;

    private AdvPagerAdapter advPagerAdapter;
    private ComicPagerAdapter comicPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        advPagerAdapter = new AdvPagerAdapter(getActivity());
        comicPagerAdapter = new ComicPagerAdapter(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);

        tabLayout = (TabLayout) v.findViewById(R.id.category_tab);
        advViewPager = (ViewPager) v.findViewById(R.id.category_adv_viewpager);
        comicViewPager = (ViewPager) v.findViewById(R.id.category_comic_viewpager);
        circleIndicator = (CircleIndicator) v.findViewById(R.id.category_indicator);
        pullRefreshLayout = (PullRefreshLayout) v.findViewById(R.id.category_refresh_layout);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComicPager();
        getAdvData();
        pullRefreshLayout.setOnRefreshListener(this);
    }

    private void initComicPager(){
        comicViewPager.setAdapter(comicPagerAdapter);
        comicViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(comicViewPager);
    }

    private void getAdvData(){
        AppDao.getInstance().getSlideData(new BaseCallbackListener<AdvModel>(){
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
                super.onStringResult(result);
                LogUtils.logI(this, "onStringResult" + result);
            }

            @Override
            public void onSuccess(AdvModel result) {
                super.onSuccess(result);
                if(result != null){
                    advPagerAdapter.updateAdvs(result.list);
                    advViewPager.setAdapter(advPagerAdapter);
                    circleIndicator.setViewPager(advViewPager);
                }
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

    }
}
