package com.github.chaossss.ishuhui.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chaossss.ishuhui.R;

/**
 * Created by chaossss on 2016/1/15.
 */
public class ComicDetailGridHolder extends RecyclerView.ViewHolder {
    private FrameLayout wrapper;

    public ImageView cover;

    public TextView title;
    public TextView subtitle;
    public TextView updateTime;

    public ComicDetailGridHolder(View itemView) {
        super(itemView);

        wrapper = (FrameLayout) itemView.findViewById(R.id.holder_comic_detail_wrapper);

        cover = (ImageView) itemView.findViewById(R.id.holder_comic_detail_cover);

        title = (TextView) itemView.findViewById(R.id.holder_comic_detail_chapter_title);
        subtitle = (TextView) itemView.findViewById(R.id.holder_comic_detail_chapter_subtitle);
        updateTime = (TextView) itemView.findViewById(R.id.holder_comic_detail_update_time);
    }

    public void setOnComicGridHolderClickListener(View.OnClickListener onComicGridHolderClickListener){
        wrapper.setOnClickListener(onComicGridHolderClickListener);
    }
}
