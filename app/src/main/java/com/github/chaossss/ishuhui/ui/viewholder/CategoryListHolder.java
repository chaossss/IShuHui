package com.github.chaossss.ishuhui.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chaossss.ishuhui.R;

import org.w3c.dom.Text;

/**
 * Created by chaos on 2016/1/9.
 */
public class CategoryListHolder extends RecyclerView.ViewHolder {
    public ImageView cover;

    public TextView title;
    public TextView intro;
    public TextView author;
    public TextView chapter;

    public CategoryListHolder(View itemView) {
        super(itemView);

        cover = (ImageView)itemView.findViewById(R.id.category_list_cover);

        title = (TextView) itemView.findViewById(R.id.category_list_title);
        intro = (TextView) itemView.findViewById(R.id.category_list_intro);
        author = (TextView) itemView.findViewById(R.id.category_list_author);
        chapter = (TextView) itemView.findViewById(R.id.category_list_chapter);
    }
}
