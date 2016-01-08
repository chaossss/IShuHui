package com.github.chaossss.ishuhui.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.chaossss.ishuhui.domain.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaos on 2016/1/8.
 */
public class ComicPagerAdapter extends FragmentPagerAdapter {
    private static final int CATEGORY_FIRST = 0;
    private static final int CATEGORY_SECOND = 1;
    private static final int CATEGORY_THIRD = 2;
    private static final int CATEGORY_NUMS = 3;

    private static final String CATEGORY_FIRST_TITLE = "热血";
    private static final String CATEGORY_SECOND_TITLE = "国产";
    private static final String CATEGORY_THIRD_TITLE = "鼠绘";

    private static List<Fragment> comicCategory = new ArrayList<>();

    public ComicPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return CATEGORY_NUMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case CATEGORY_FIRST:
                return CATEGORY_FIRST_TITLE;

            case CATEGORY_SECOND:
                return CATEGORY_SECOND_TITLE;

            case CATEGORY_THIRD:
                return CATEGORY_THIRD_TITLE;

            default:
                return StringUtils.EMPTY_STR;
        }
    }
}
