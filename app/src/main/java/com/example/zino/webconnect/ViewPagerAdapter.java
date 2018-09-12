package com.example.zino.webconnect;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{
    Fragment[] fragments = new Fragment[4];

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments[0] = new ListFragment();
        fragments[1] = new ThreadFragment();
        fragments[2] = new URLImgFragment();
        fragments[3] = new AsyncFragment();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
