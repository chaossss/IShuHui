package com.github.chaossss.ishuhui.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.chaossss.ishuhui.domain.util.StringUtils;
import com.github.chaossss.ishuhui.ui.fragment.CategoryFragment;
import com.github.chaossss.ishuhui.ui.fragment.CategoryListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaos on 2016/1/8.
 */
public class ComicPagerAdapter extends FragmentPagerAdapter {
    private static List<Fragment> comicCategory = new ArrayList<>();

    public ComicPagerAdapter(FragmentManager fm) {
        super(fm);
        comicCategory.add(CategoryListFragment.newInstance(CategoryListFragment.CATEGORY_FIRST));
        comicCategory.add(CategoryListFragment.newInstance(CategoryListFragment.CATEGORY_SECOND));
        comicCategory.add(CategoryListFragment.newInstance(CategoryListFragment.CATEGORY_THIRD));
    }

    @Override
    public Fragment getItem(int position) {
        return comicCategory.get(position);
    }

    @Override
    public int getCount() {
        return CategoryListFragment.CATEGORY_NUMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case CategoryListFragment.CATEGORY_FIRST:
                return CategoryListFragment.CATEGORY_FIRST_TITLE;

            case CategoryListFragment.CATEGORY_SECOND:
                return CategoryListFragment.CATEGORY_SECOND_TITLE;

            case CategoryListFragment.CATEGORY_THIRD:
                return CategoryListFragment.CATEGORY_THIRD_TITLE;

            default:
                return CategoryListFragment.CATEGORY_FIRST_TITLE;
        }
    }
}
