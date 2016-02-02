package com.github.chaossss.ishuhui.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.model.ComicDetailModel;
import com.github.chaossss.ishuhui.domain.url.ShuHuiURL;
import com.github.chaossss.ishuhui.domain.util.StringUtils;
import com.github.chaossss.ishuhui.ui.activity.WebActivity;
import com.github.chaossss.ishuhui.ui.viewholder.ComicDetailGridHolder;

import java.util.List;

/**
 * Created by chaos on 2016/1/15.
 */
public class ComicGridAdapter extends RecyclerView.Adapter<ComicDetailGridHolder> {
    private Context context;
    private List<ComicDetailModel.ReturnEntity.ListEntity> comicGridList;

    public ComicGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ComicDetailGridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_comic_detail_grid_item, parent, false);
        return new ComicDetailGridHolder(v);
    }

    @Override
    public void onBindViewHolder(ComicDetailGridHolder holder, int position) {
        final String url = ShuHuiURL.URL_IMG_CHAPTER + comicGridList.get(position).Id;

        holder.subtitle.setText(comicGridList.get(position).Title);
        holder.updateTime.setText(comicGridList.get(position).RefreshTimeStr);
        switch(comicGridList.get(position).ChapterType){
            case 0:
                holder.title.setText(StringUtils.generateStr(comicGridList.get(position).Sort + StringUtils.CHAPTER_SUFFIX_1));
                break;

            case 1:
                holder.title.setText(StringUtils.generateStr(comicGridList.get(position).Sort + StringUtils.CHAPTER_SUFFIX_2));
                break;

            case 2:
                holder.title.setText(StringUtils.generateStr(comicGridList.get(position).Sort + StringUtils.CHAPTER_SUFFIX_3));
                break;
        }
        Glide.with(context).load(comicGridList.get(position).FrontCover).centerCrop().into(holder.cover);
        holder.setOnComicGridHolderClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick(url);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comicGridList == null ? 0 : comicGridList.size();
    }

    public void updateData(List<ComicDetailModel.ReturnEntity.ListEntity> comicGridList) {
        this.comicGridList = comicGridList;
        this.notifyDataSetChanged();
    }

    private void handleClick(String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WebActivity.EXTRA_URL, url);
        context.startActivity(intent);
    }
}
