package com.kakaobank.assignment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kakaobank.assignment.R;
import com.kakaobank.assignment.databinding.MainActivityBinding;
import com.kakaobank.assignment.service.ImageSearchService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private MainPresentation presentation;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        presentation = ViewModelProviders.of(this).get(MainPresentation.class);
        binding.setPresentation(presentation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setViewPagerAndTabLayout();
        setEventHandler(binding);
    }

    private void setEventHandler(MainActivityBinding binding) {
        presentation.searchKeywordEvent.observe(this, keyword -> {
            Snackbar.make(binding.getRoot(), R.string.loading_images, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            Intent intent = new Intent(MainActivity.this, ImageSearchService.class);
            startService(intent);
        });
    }

    private void setViewPagerAndTabLayout() {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addTab(tabLayout.newTab().setText("검색"));
        tabLayout.addTab(tabLayout.newTab().setText("북마크"));
        tabLayout.addOnTabSelectedListener(new TabSelectedListener(viewPager));
    }

}
