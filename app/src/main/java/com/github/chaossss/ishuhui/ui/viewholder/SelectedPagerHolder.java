package com.github.chaossss.ishuhui.ui.viewholder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.chaossss.ishuhui.R;

/**
 * Created by chaos on 2016/1/25.
 */
public class SelectedPagerHolder {
    public TextView name;
    public TextView intro;
    public TextView author;
    public TextView subTitle;

    public ImageView cover;

    private FrameLayout wrapper;
    
    public SelectedPagerHolder(View root) {
        name = (TextView) root.findViewById(R.id.holder_selected_name);
        intro = (TextView) root.findViewById(R.id.holder_selected_intro);
        author = (TextView) root.findViewById(R.id.holder_selected_author);
        subTitle = (TextView) root.findViewById(R.id.holder_selected_subtitle);

        cover = (ImageView) root.findViewById(R.id.holder_selected_cover);

        wrapper = (FrameLayout) root.findViewById(R.id.holder_selected_wrapper);
    }

    public void setOnSelectedPagerHolderClickListener(View.OnClickListener onSelectedPagerHolderClickListener){
        wrapper.setOnClickListener(onSelectedPagerHolderClickListener);
    }
}
