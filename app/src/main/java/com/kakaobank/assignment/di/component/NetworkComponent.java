package com.kakaobank.assignment.di.component;

import com.kakaobank.assignment.di.module.NetworkModule;
import com.kakaobank.assignment.service.ImageSearchService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(ImageSearchService imageSearchService);
}
