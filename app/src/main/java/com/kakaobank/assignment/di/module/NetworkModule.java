package com.kakaobank.assignment.di.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;

@Module
public class NetworkModule {
    private String headerKey;
    private String headerValue;

    public NetworkModule(String headerKey, String headerValue) {
        this.headerKey = headerKey;
        this.headerValue = headerValue;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .addHeader(headerKey, headerValue)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }).addNetworkInterceptor(new StethoInterceptor())
                .build();
    }
}
