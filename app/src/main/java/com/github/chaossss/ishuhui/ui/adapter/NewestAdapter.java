package com.github.chaossss.ishuhui.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.chaossss.httplibrary.listener.BaseCallbackListener;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.dao.AppDao;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.domain.model.SubscribeModel;
import com.github.chaossss.ishuhui.domain.url.ShuHuiURL;
import com.github.chaossss.ishuhui.domain.util.SPUtils;
import com.github.chaossss.ishuhui.domain.util.StringUtils;
import com.github.chaossss.ishuhui.ui.activity.WebActivity;
import com.github.chaossss.ishuhui.ui.util.ToastUtils;
import com.github.chaossss.ishuhui.ui.viewholder.NewestViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaos on 2016/1/4.
 */
public class NewestAdapter extends RecyclerView.Adapter<NewestViewHolder> {
    private static final String CHAPTER = "话";
    private static final String DIVIDER = "  ";

    private Context context;
    private List<AllBookModels.ReturnClazz.AllBook> newestList;

    public NewestAdapter(Context context, List<AllBookModels.ReturnClazz.AllBook> newestList) {
        this.context = context;
        this.newestList = newestList;
    }

    public void updateData(List<AllBookModels.ReturnClazz.AllBook> newestList) {
        this.newestList = newestList;
        this.notifyDataSetChanged();
    }

    @Override
    public NewestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_newest_item, parent, false);
        return new NewestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final NewestViewHolder holder, int position) {
        final AllBookModels.ReturnClazz.AllBook allBook = newestList.get(position);

        if(allBook.LastChapter == null){
            return;
        }

        holder.badgedView.setBadgeText("新");
        holder.subtitle.setText(allBook.LastChapter.Title);
        holder.updateTime.setText(allBook.LastChapter.RefreshTimeStr);
        holder.title.setText(StringUtils.generateStr(allBook.Title + DIVIDER + allBook.LastChapter.ChapterNo + CHAPTER));

        Glide.with(context).load(allBook.LastChapter.FrontCover).centerCrop().into(holder.cover);

        if(SPUtils.getObject("id" + allBook.Id, String.class, "-1").equals(allBook.LastChapter.ChapterNo)) {
            holder.badgedView.showBadge(false);
        } else {
            holder.badgedView.showBadge(true);
        }

        holder.setOnNewestHolderClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handClick(ShuHuiURL.URL_IMG_CHAPTER + allBook.LastChapter.Id, allBook.LastChapter.Title);

                SPUtils.saveObject(allBook.Id, allBook.LastChapter.ChapterNo);
            }
        });

        if(SPUtils.getObject("id" + allBook.Id, String.class, "-1").equals(allBook.Id)) {
            holder.subscribe.setText(allBook.Author);
        } else {
            holder.subscribe.setText("+订阅");
        }

        holder.subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDao.getInstance().subscribeBook(allBook.LastChapter.Id, true, new BaseCallbackListener<SubscribeModel>()
                {
                    @Override
                    public void onStringResult(String result) {
                        super.onStringResult(result);
                        Log.i("sub", "res------>" + result);
                    }

                    @Override
                    public void onSuccess(SubscribeModel result) {
                        super.onSuccess(result);
                        ToastUtils.showToast(context, StringUtils.generateStr("您的喜欢，" + allBook.Author + "已经知道了"));
                        SPUtils.saveObject("id" + allBook.Id, allBook.Id);
                        holder.subscribe.setText(allBook.Author);
                    }

                    @Override
                    public void onError(Exception e) {
                        super.onError(e);
                        ToastUtils.showToast(context, "订阅失败");
                    }
                });
            }
        });
    }

    private void handClick(String url,String title) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_URL, url);
        intent.putExtra(WebActivity.EXTRA_TITLE,title);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return newestList == null ? 0 : newestList.size();
    }
}
