package com.github.chaossss.ishuhui.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.model.CategoryModel;
import com.github.chaossss.ishuhui.domain.util.StringUtils;
import com.github.chaossss.ishuhui.ui.viewholder.CategoryListHolder;

import java.util.List;

/**
 * Created by chaos on 2016/1/9.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListHolder> {
    private Context context;
    private List<CategoryModel.ReturnEntity.ListEntity> categoryListDatas;

    public CategoryListAdapter(Context context, List<CategoryModel.ReturnEntity.ListEntity> categoryListDatas) {
        this.context = context;
        this.categoryListDatas = categoryListDatas;
    }

    @Override
    public CategoryListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_category_list_fragment_item, parent, false);
        return new CategoryListHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryListHolder holder, int position) {
        holder.title.setText(categoryListDatas.get(position).Title);
        holder.intro.setText(categoryListDatas.get(position).Explain);
        holder.author.setText(categoryListDatas.get(position).Author);
        holder.chapter.setText(StringUtils.generateStr(StringUtils.NO,
                String.valueOf(categoryListDatas.get(position).LastChapterNo), StringUtils.CHAPTER));
        Glide.with(context).load(categoryListDatas.get(position).FrontCover).centerCrop().into(holder.cover);
    }

    @Override
    public int getItemCount() {
        return categoryListDatas == null ? 0 : categoryListDatas.size();
    }

    public void updateDatas(List<CategoryModel.ReturnEntity.ListEntity> categoryListDatas){
        this.categoryListDatas = categoryListDatas;
        notifyDataSetChanged();
    }
}
