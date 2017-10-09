package com.binary.giphy.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.binary.giphy.models.Category;

import java.util.List;

/**
 * Created by duong on 9/27/2017.
 */

public class MainAdapter extends FragmentPagerAdapter{
    private Context context;
    List<Fragment> fragments;
    List<Category> categories;
    public MainAdapter(FragmentManager fm, Context context, List<Fragment> fragments, List<Category> categories) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.categories = categories;

        Log.e("csh", String.valueOf(categories.size()));
        Log.e("csh", String.valueOf(fragments.size()));

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }
}
