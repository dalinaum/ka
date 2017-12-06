package com.kakaobank.assignment.di.component;

import com.kakaobank.assignment.di.module.GsonParserModule;
import com.kakaobank.assignment.di.module.KakaoModule;
import com.kakaobank.assignment.di.module.NetworkModule;
import com.kakaobank.assignment.service.ImageSearchService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, GsonParserModule.class, KakaoModule.class})
public interface NetworkComponentWithGSON {
    void inject(ImageSearchService imageSearchService);
}
