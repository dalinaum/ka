package com.kakaobank.assignment.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

class TabSelectedListener implements TabLayout.OnTabSelectedListener {
    private ViewPager viewPager;

    public TabSelectedListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        viewPager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
