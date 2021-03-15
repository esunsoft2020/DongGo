package com.esunsoft2020.donggo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class BranchTabAdapter extends FragmentPagerAdapter {

    Fragment[] fragments;
    String[] titles;

    public BranchTabAdapter(@NonNull FragmentManager fm, int behavior, String[] titles) {
        super(fm, behavior);
        fragments = new Fragment[titles.length];
        this.titles = titles;

        for(int i =0 ;i<titles.length;i++){
            fragments[i] = new BranchFragment();
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
