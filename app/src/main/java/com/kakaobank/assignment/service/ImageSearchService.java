package com.kakaobank.assignment.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.kakaobank.assignment.BuildConfig;
import com.kakaobank.assignment.entity.Results;
import com.kakaobank.assignment.entity.util.DocumentUtil;
import com.kakaobank.assignment.entity.util.RealmUtil;

import java.io.IOException;

import io.realm.Realm;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageSearchService extends IntentService {
    private static final String TAG = "ImageSearchService";
    private static final String AUTHORIZATION = "Authorization";
    private static final String BASE_URL = "https://dapi.kakao.com";
    private static final String AUTHORIZATION_PREFIX = "KakaoAK ";
    private final String authorizationValue;

    public ImageSearchService() {
        super(TAG);
        authorizationValue = generateAuthorizationValue();
    }

    @NonNull
    private String generateAuthorizationValue() {
        StringBuilder stringBuilder = new StringBuilder(AUTHORIZATION_PREFIX);
        stringBuilder.append(BuildConfig.KAKAO_API_KEY);
        return stringBuilder.toString();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        KakaoService kakaoAPI = newRetrofitInstance().create(KakaoService.class);
        retrofit2.Response<Results> results;
        try {
            results = kakaoAPI.images("설현", 1).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Realm realm = RealmUtil.getRealmInstance();
        Log.d(TAG, "results body size:" + results.body().documents.size());
        DocumentUtil.addDocuments(realm, results.body().documents);
        realm.close();
    }

    @NonNull
    private Retrofit newRetrofitInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .addHeader(AUTHORIZATION, authorizationValue)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }).addNetworkInterceptor(new StethoInterceptor())
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
