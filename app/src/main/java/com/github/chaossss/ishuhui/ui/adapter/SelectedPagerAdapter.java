package com.github.chaossss.ishuhui.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chaossss.ishuhui.R;
import com.github.chaossss.ishuhui.domain.model.AllBookModels;
import com.github.chaossss.ishuhui.ui.activity.ComicDetailActivity;
import com.github.chaossss.ishuhui.ui.viewholder.SelectedPagerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaos on 2016/1/25.
 */
public class SelectedPagerAdapter extends PagerAdapter {
    private Context context;
    private List<AllBookModels.ReturnClazz.AllBook> selectedComicList;

    public SelectedPagerAdapter(Context context) {
        this.context = context;
        selectedComicList = new ArrayList<>();
    }

    public void addSelectedComicList(List<AllBookModels.ReturnClazz.AllBook> selectedComicList) {
        this.selectedComicList.addAll(selectedComicList);
        notifyDataSetChanged();
    }

    public void resetSelectedComicList(List<AllBookModels.ReturnClazz.AllBook> selectedComicList) {
        this.selectedComicList = new ArrayList<>(selectedComicList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return selectedComicList == null ? 0 : selectedComicList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View root = LayoutInflater.from(context).inflate(R.layout.layout_selected_pager_item, container, false);
        SelectedPagerHolder holder = new SelectedPagerHolder(root);
        container.addView(root);

        holder.name.setText(selectedComicList.get(position).Title);
        holder.subTitle.setText(selectedComicList.get(position).LastChapter.Title);
        holder.author.setText(selectedComicList.get(position).Author);
        holder.intro.setText(selectedComicList.get(position).Explain);
        Glide.with(context).load(selectedComicList.get(position).FrontCover).centerCrop().into(holder.cover);
        holder.setOnSelectedPagerHolderClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ComicDetailActivity.class);
                intent.putExtra(ComicDetailActivity.COMIC_ID, String.valueOf(selectedComicList.get(position).Id));
                context.startActivity(intent);
            }
        });
        
        return root;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
