package com.github.chaossss.ishuhui.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chaossss.ishuhui.R;
import com.github.chaossss.widget.view.BadgedView;

/**
 * Created by chaos on 2016/1/7.
 */
public class NewestViewHolder extends RecyclerView.ViewHolder {
    private FrameLayout wrapper;

    public ImageView cover;
    public BadgedView badgedView;

    public TextView title;
    public TextView subtitle;
    public TextView subscribe;
    public TextView updateTime;

    public NewestViewHolder(View itemView) {
        super(itemView);

        wrapper = (FrameLayout) itemView.findViewById(R.id.holder_newest_wrapper);

        cover = (ImageView) itemView.findViewById(R.id.holder_newest_cover);
        badgedView = (BadgedView) itemView.findViewById(R.id.holder_newest_cover_badge);

        title = (TextView) itemView.findViewById(R.id.holder_newest_latest_chapter_title);
        subtitle = (TextView) itemView.findViewById(R.id.holder_newest_latest_chapter_subtitle);
        subscribe = (TextView) itemView.findViewById(R.id.holder_newest_subscribe);
        updateTime = (TextView) itemView.findViewById(R.id.holder_newest_latest_update_time);
    }

    public void setOnNewestHolderClickListener(View.OnClickListener onNewestHolderClickListener){
        wrapper.setOnClickListener(onNewestHolderClickListener);
    }
}
