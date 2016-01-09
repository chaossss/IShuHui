package com.github.chaossss.ishuhui.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chaossss.ishuhui.domain.model.AdvModel;
import com.github.chaossss.ishuhui.ui.activity.WebActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaos on 2016/1/8.
 */
public class AdvPagerAdapter extends PagerAdapter {
    private Context context;
    private List<ImageView>  advWrappers;
    private List<AdvModel.ListEntity> advs;

    public AdvPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return advs == null ? 0 : advs.size();
    }

    public void updateAdvs(List<AdvModel.ListEntity> advs){
        this.advs = advs;

        if(advs != null && advs.size() != 0){
            advWrappers = new ArrayList<>();

            for(int i = 0; i < advs.size(); i++){
                advWrappers.add(new ImageView(context));
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView advWrapper = advWrappers.get(position);
        advWrapper.setAdjustViewBounds(true);
        advWrapper.setScaleType(ImageView.ScaleType.FIT_XY);
        advWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra(WebActivity.EXTRA_URL, advs.get(position).Link);
                context.startActivity(intent);
            }
        });

        Glide.with(context).load(advs.get(position).Img).centerCrop().into(advWrapper);
        container.addView(advWrapper, 0);

        return advWrapper;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(advWrappers.get(position));
    }
}
