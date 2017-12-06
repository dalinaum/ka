package com.kakaobank.assignment.main.listener;

import android.support.v4.view.ViewPager;
import android.view.View;

class SearchBarOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private final View searchBar;

    public SearchBarOnPageChangeListener(View searchBar) {
        this.searchBar = searchBar;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            searchBar.setVisibility(View.VISIBLE);
        } else {
            searchBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
