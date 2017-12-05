package com.kakaobank.assignment.service;

import com.kakaobank.assignment.entity.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KakaoService {

    String SEARCH_IMAGE = "v2/search/image";
    String KEY_QUERY = "query";
    String KEY_PAGE = "page";

    @GET(SEARCH_IMAGE)
    Call<Results> images(@Query(KEY_QUERY) String query, @Query(KEY_PAGE) int page);
}
