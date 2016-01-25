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
import com.github.chaossss.pianoview.PianoAdapter;
import com.github.chaossss.pianoview.PianoItemListener;
import com.github.chaossss.pianoview.PianoView;

import java.util.ArrayList;
import java.util.List;

public class SelectedFragment extends Fragment implements PianoItemListener, ViewPager.OnPageChangeListener {
    private PianoView pianoView;
    private ViewPager selectedPager;
    private SelectedPagerAdapter selectedPagerAdapter;

    private PianoAdapter pianoAdapter;
    private List<String> pianoIconList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pianoIconList = new ArrayList<>();
        selectedPagerAdapter = new SelectedPagerAdapter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_selected, container, false);

        selectedPager = (ViewPager) root.findViewById(R.id.selected_pager);
        selectedPager.setAdapter(selectedPagerAdapter);
        selectedPager.addOnPageChangeListener(this);

        pianoView = (PianoView) root.findViewById(R.id.selected_piano_view);
        pianoView.setPianoItemListenerListener(SelectedFragment.this);
        pianoAdapter = new PianoAdapter(getContext(), pianoView);
        pianoView.setAdapter(pianoAdapter);

        getSelectedComic();

        return root;
    }

    private void getSelectedComic(){
        AppDao.getInstance().subscribeByUser("0", new BaseCallbackListener<AllBookModels>() {
            @Override
            public void onStringResult(String result) {
                super.onStringResult(result);
            }

            @Override
            public void onSuccess(AllBookModels result) {
                super.onSuccess(result);

                for(AllBookModels.ReturnClazz.AllBook comic : result.Return.List){
                    pianoIconList.add(comic.FrontCover);
                }
                pianoAdapter.addIconUrlList(pianoIconList);
                selectedPagerAdapter.addSelectedComicList(result.Return.List);
                pianoView.showPianoAtPosition(0);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
            }
        });
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
}
