package com.kakaobank.assignment.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kakaobank.assignment.R;
import com.kakaobank.assignment.databinding.MainActivityBinding;
import com.kakaobank.assignment.main.listener.SearchBarOnPageChangeListener;
import com.kakaobank.assignment.main.listener.TabSelectedListener;
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
        initViewPagerAndTabLayout();
        setupEventHandler(binding);
    }

    private void setupEventHandler(MainActivityBinding binding) {
        presentation.searchKeywordEvent.observe(this, keyword -> {
            Snackbar.make(binding.getRoot(), R.string.loading_images, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            ImageSearchService.startService(this, keyword);
        });
    }

    private void initViewPagerAndTabLayout() {
        viewPager = findViewById(R.id.container);
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_search));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_bookmark));

        View searchBar = findViewById(R.id.searchBar);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new SearchBarOnPageChangeListener(searchBar));
        tabLayout.addOnTabSelectedListener(new TabSelectedListener(viewPager));
    }

}
