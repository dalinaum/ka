package com.kakaobank.assignment.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kakaobank.assignment.images.ImagesFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ImagesFragment();
        }
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
