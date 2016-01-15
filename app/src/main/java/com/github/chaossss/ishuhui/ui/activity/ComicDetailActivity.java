package com.github.chaossss.ishuhui.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.BaseApplication;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;
import com.github.chaossss.ishuhui.domain.model.SubscribeModel;
import com.github.chaossss.ishuhui.domain.url.ShuHuiURL;
import com.github.chaossss.ishuhui.domain.util.SPUtils;
import com.github.chaossss.ishuhui.domain.util.StringUtils;
import com.github.chaossss.ishuhui.ui.custom.NormalGridView;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;

import java.util.List;

/**
 * Created by chaos on 2016/1/15.
 */
public class ComicDetailActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, AdapterView.OnItemClickListener {
    public static final String COMIC_ID = "comic_id";
    private String comicID;

    private Toolbar toolbar;
    private ImageView comicCover;
    private NormalGridView comicGrid;
    private FloatingActionButton subscribe;
    private NestedScrollView nestedScrollView;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private int pageIndex;
    private boolean isLoadMore;
    private boolean isSubscribe;
    private boolean isHaveBottom = false;
    private List<ComicDetailModel.ReturnEntity.ListEntity> comicDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);

        toolbar = (Toolbar) findViewById(R.id.comic_detail_toolbar);
        comicCover = (ImageView) findViewById(R.id.comic_detail_comic_cover);
        comicGrid = (NormalGridView) findViewById(R.id.comic_detail_comic_grid);
        subscribe = (FloatingActionButton) findViewById(R.id.comic_detail_subscribe);
        nestedScrollView = (NestedScrollView) findViewById(R.id.comic_detail_nested_scroll_view);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.comic_detail_collapsing_toolbar);

        setSupportActionBar(toolbar);
        subscribe.setOnClickListener(this);
        comicGrid.setOnItemClickListener(this);
        nestedScrollView.setOnTouchListener(this);
        comicID = getIntent().getStringExtra(COMIC_ID);
    }

    private void getComicDetailData(){
        AppDao.getInstance().getBookComicData(comicID, String.valueOf(pageIndex), new BaseCallbackListener<ComicDetailModel>() {
            @Override
            public void onSuccess(ComicDetailModel result) {
                super.onSuccess(result);
                showView(result);
            }
        });
    }

    private void showView(ComicDetailModel comicDetailModel) {
        if(!isLoadMore) {
            comicDetailList = comicDetailModel.Return.List;
            collapsingToolbarLayout.setTitle(comicDetailModel.Return.ParentItem.Title);
            Glide.with(this).load(comicDetailModel.Return.ParentItem.FrontCover).centerCrop().into(comicCover);
            mDetialBookAdapter = new DetialBookAdapter(this, comicDetailList);
            comicGrid.setAdapter(mDetialBookAdapter);
        } else {
            comicDetailList.addAll(comicDetailModel.Return.List);
            mDetialBookAdapter.updateData(comicDetailList);
            isLoadMore = false;
            isHaveBottom = false;
        }

        if(SPUtils.getObject(BaseApplication.UserInfo.email+"id" + comicID, String.class, "-1").equals(comicID)) {
            isSubscribe = false;
            subscribe.setImageResource(R.mipmap.ic_done);
        } else {
            isSubscribe = true;
            subscribe.setImageResource(R.mipmap.ic_add_white_24dp);
        }
    }

    private void handleClick(String url) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_URL, url);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(!StringUtils.isValid(comicID)) {
            return;
        }

        AppDao.getInstance().subscribeBook(comicID, isSubscribe, new BaseCallbackListener<SubscribeModel>() {

            @Override
            public void onSuccess(SubscribeModel result) {
                super.onSuccess(result);
                if(isSubscribe) {
                    ToastUtils.showToast(ComicDetailActivity.this, "订阅成功");
                    SPUtils.saveObject(BaseApplication.UserInfo.email + "id" + comicID, comicID);
                    subscribe.setImageResource(R.mipmap.ic_done);
                    isSubscribe = false;
                } else {
                    ToastUtils.showToast(ComicDetailActivity.this, "已取消订阅");
                    SPUtils.saveObject(BaseApplication.UserInfo.email + "id" + comicID, "-2");
                    subscribe.setImageResource(R.mipmap.ic_add_white_24dp);
                    isSubscribe = true;
                }
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                ToastUtils.showToast(ComicDetailActivity.this, "订阅失败");
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        handleClick(ShuHuiURL.URL_IMG_CHAPTER + mDetialBookAdapter.getItem(position).Id);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            int height = v.getHeight();
            int scrollY = v.getScrollY();
            int scrollViewMeasuredHeight = nestedScrollView.getChildAt(0).getMeasuredHeight();
            if((scrollY + height) == scrollViewMeasuredHeight) {
                if(!isHaveBottom) {
                    pageIndex++;
                    isLoadMore = true;
                    getComicDetailData();
                }
                isHaveBottom = true;
            }
        }
        return false;
    }
}
