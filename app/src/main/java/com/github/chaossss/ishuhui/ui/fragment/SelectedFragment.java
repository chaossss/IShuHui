package com.github.chaossss.ishuhui.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.ui.adapter.SelectedPagerAdapter;
import com.github.chaossss.ishuhui.ui.presenter.selected.SelectedPresenter;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;
import com.github.chaossss.pianoview.PianoAdapter;
import com.github.chaossss.pianoview.PianoItemListener;
import com.github.chaossss.pianoview.PianoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectedFragment extends Fragment implements SelectedPresenter.View, PianoItemListener, ViewPager.OnPageChangeListener {
    @Bind(R.id.selected_piano_view)
    PianoView pianoView;
    @Bind(R.id.selected_pager)
    ViewPager selectedPager;

    private SelectedPresenter presenter;

    private SelectedPagerAdapter selectedPagerAdapter;

    private PianoAdapter pianoAdapter;
    private List<String> pianoIconList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new SelectedPresenter(this);

        pianoIconList = new ArrayList<>();
        selectedPagerAdapter = new SelectedPagerAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_selected, container, false);
        ButterKnife.bind(this, root);

        selectedPager.setAdapter(selectedPagerAdapter);
        selectedPager.addOnPageChangeListener(this);

        pianoView.setPianoItemListenerListener(SelectedFragment.this);
        pianoAdapter = new PianoAdapter(getContext(), pianoView);
        pianoView.setAdapter(pianoAdapter);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.getSelectedComic();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPianoItemSelected(int itemIndex) {
        selectedPager.setCurrentItem(itemIndex);
        pianoView.showPianoAtPosition(itemIndex);
    }

    @Override
    public void onStartSwipe() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pianoView.showPianoAtPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSelectedComicGotSuccess(List<AllBookModels.ReturnClazz.AllBook> selectedComicList) {
        for(AllBookModels.ReturnClazz.AllBook comic : selectedComicList){
            pianoIconList.add(comic.FrontCover);
        }
        pianoAdapter.addIconUrlList(pianoIconList);
        selectedPagerAdapter.addSelectedComicList(selectedComicList);
        pianoView.showPianoAtPosition(0);
    }

    @Override
    public void onSelectedComicGotFail(String errorInfo) {
        ToastUtils.showToast(getContext(), errorInfo);
    }
}
