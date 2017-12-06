package com.kakaobank.assignment.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kakaobank.assignment.BuildConfig;
import com.kakaobank.assignment.di.component.DaggerNetworkComponent;
import com.kakaobank.assignment.di.module.NetworkModule;
import com.kakaobank.assignment.entity.Results;
import com.kakaobank.assignment.entity.util.DocumentUtil;
import com.kakaobank.assignment.entity.util.RealmUtil;
import com.kakaobank.assignment.entity.util.SearchTargetUtil;

import java.io.IOException;

import javax.inject.Inject;

import io.realm.Realm;

public class ImageSearchService extends IntentService {
    public static final String INTENT_KEY_KEYWORD = "INTENT_KEY_KEYWORD";
    public static final String INTENT_KEY_PAGE = "INTENT_KEY_PAGE";
    private static final String TAG = "ImageSearchService";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String AUTHORIZATION_PREFIX = "KakaoAK ";

    @Inject
    KakaoService kakaoService;

    public ImageSearchService() {
        super(TAG);
    }

    public static void startService(Activity activity, String keyword, int page) {
        Intent intent = new Intent(activity, ImageSearchService.class);
        intent.putExtra(ImageSearchService.INTENT_KEY_KEYWORD, keyword);
        intent.putExtra(ImageSearchService.INTENT_KEY_PAGE, page);
        activity.startService(intent);
    }

    public static void startService(Activity activity, String keyword) {
        startService(activity, keyword, 1);
    }

    @NonNull
    private String generateAuthorizationValue() {
        StringBuilder stringBuilder = new StringBuilder(AUTHORIZATION_PREFIX);
        stringBuilder.append(BuildConfig.KAKAO_API_KEY);
        return stringBuilder.toString();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(AUTHORIZATION, generateAuthorizationValue(), BASE_URL))
                .build()
                .inject(this);

        Log.d(TAG, "handleIntent");
        Bundle extras = intent.getExtras();
        String keyword = extras.getString(INTENT_KEY_KEYWORD);

        Log.d(TAG, "keyword: " + keyword);

        if (keyword == null || keyword.length() == 0) {
            return;
        }

        int page = extras.getInt(INTENT_KEY_PAGE, 1);

        Log.d(TAG, "page: " + page);

        if (page == 1) {
            Realm realm = RealmUtil.getRealmInstance();
            DocumentUtil.deleteAllDocuments(realm);
            realm.close();
        }

        retrofit2.Response<Results> results;
        try {
            results = kakaoService.images(keyword, page).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Results body = results.body();
        if (body == null) {
            results.raw().body().close();
            return;
        }

        Realm realm = RealmUtil.getRealmInstance();
        DocumentUtil.addDocuments(realm, body.documents);
        SearchTargetUtil.setKeywordAndPage(realm, keyword, page);
        realm.close();
    }
}
