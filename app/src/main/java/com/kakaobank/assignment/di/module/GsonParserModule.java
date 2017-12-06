package com.kakaobank.assignment.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GsonParserModule {
    @Provides
    @Singleton
    Converter.Factory provideConvertFactory() {
        return GsonConverterFactory.create();
    }
}
