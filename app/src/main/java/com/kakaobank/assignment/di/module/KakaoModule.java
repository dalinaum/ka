package com.kakaobank.assignment.di.module;

import com.kakaobank.assignment.service.KakaoService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

@Module
public class KakaoModule {
    private String baseUrl;

    public KakaoModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory factory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(factory)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    KakaoService provideKakaoService(Retrofit retrofit) {
        return retrofit.create(KakaoService.class);
    }
}