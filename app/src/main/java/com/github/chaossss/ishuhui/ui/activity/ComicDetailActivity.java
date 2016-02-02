package com.github.chaossss.ishuhui.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.AppConstant;
import com.github.chaossss.ishuhui.domain.BaseApplication;
import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;
import com.github.chaossss.ishuhui.domain.util.SPUtils;
import com.github.chaossss.ishuhui.domain.util.StringUtils;
import com.github.chaossss.ishuhui.ui.adapter.ComicGridAdapter;
import com.github.chaossss.ishuhui.ui.presenter.comic_detail.ComicDetailPresenter;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ComicDetailActivity extends AppCompatActivity implements ComicDetailPresenter.View, View.OnClickListener {
    public static final String COMIC_ID = "comic_id";
    private String comicID;

    @Bind(R.id.comic_detail_toolbar)
    Toolbar toolbar;
    @Bind(R.id.comic_detail_comic_cover)
    ImageView comicCover;
    @Bind(R.id.comic_detail_comic_grid)
    RecyclerView comicGrid;
    @Bind(R.id.comic_detail_subscribe)
    FloatingActionButton subscribe;
    @Bind(R.id.comic_detail_collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private ComicDetailPresenter presenter;

    private int pageIndex;
    private boolean isLoadMore;
    private boolean isSubscribe;
    private List<ComicDetailModel.ReturnEntity.ListEntity> comicDetailList;

    private StaggeredGridLayoutManager sgm;
    private ComicGridAdapter comicGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        ButterKnife.bind(this);

        presenter = new ComicDetailPresenter(this);

        setSupportActionBar(toolbar);
        subscribe.setOnClickListener(this);
        comicGridAdapter = new ComicGridAdapter(this);
        comicID = getIntent().getStringExtra(COMIC_ID);

        sgm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        comicGrid.setLayoutManager(sgm);
        comicGrid.setAdapter(comicGridAdapter);
        presenter.getComicDetail(comicID, String.valueOf(pageIndex));

        subscribe.attachToRecyclerView(comicGrid);
        comicGrid.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int[] visibleItems = sgm.findLastVisibleItemPositions(null);
                int lastItem = Math.max(visibleItems[0], visibleItems[1]);

                if(dy > 0){
                    subscribe.hide();

                    if(lastItem >= sgm.getItemCount() / 3 * 2 && isLoadMore){
                        presenter.getComicDetail(comicID, String.valueOf(pageIndex));
                    }
                } else {
                    subscribe.show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void showView(ComicDetailModel comicDetailModel) {
        if(!isLoadMore) {
            comicDetailList = comicDetailModel.Return.List;
            collapsingToolbarLayout.setTitle(comicDetailModel.Return.ParentItem.Title);
            Glide.with(this).load(comicDetailModel.Return.ParentItem.FrontCover).centerCrop().into(comicCover);
            comicGridAdapter.updateData(comicDetailList);
            isLoadMore = true;
        } else {
            comicDetailList.addAll(comicDetailModel.Return.List);
            comicGridAdapter.updateData(comicDetailList);
        }

        pageIndex++;

        if(SPUtils.getObject(BaseApplication.UserInfo.email+  "id" + comicID, String.class, "-1").equals(comicID)) {
            isSubscribe = true;
            subscribe.setImageResource(R.mipmap.ic_done);
        } else {
            isSubscribe = false;
            subscribe.setImageResource(R.mipmap.ic_add_white_24dp);
        }
    }

    @Override
    public void onClick(View v) {
        subscribeComic();
    }

    private void subscribeComic(){
        if(!StringUtils.isValid(comicID)) {
            return;
        }

        presenter.subscribeComic(comicID, isSubscribe);
    }

    @Override
    public void onComicDetailGotSuccess(ComicDetailModel comicDetailModel) {
        showView(comicDetailModel);
    }

    @Override
    public void onComicDetailGotFail(String errorInfo) {
        ToastUtils.showToast(this, errorInfo);
    }

    @Override
    public void onComicSubscribedSuccess(boolean isSubscribed) {
        if (isSubscribed) {
            ToastUtils.showToast(this, AppConstant.SUBSCRIBE_SUCCESS);
            subscribe.setImageResource(R.mipmap.ic_done);
            isSubscribe = true;
        } else {
            ToastUtils.showToast(this, AppConstant.SUBSCRIBE_CANCEL);
            subscribe.setImageResource(R.mipmap.ic_add_white_24dp);
            isSubscribe = false;
        }
    }

    @Override
    public void onComicSubscribedFail(String errorInfo) {
        ToastUtils.showToast(this, errorInfo);
    }
}
