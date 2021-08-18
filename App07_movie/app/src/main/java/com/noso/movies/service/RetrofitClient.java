package com.noso.movies.service;

import com.noso.movies.config.NaverAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit getInstance() {
        return new Retrofit.Builder().baseUrl(NaverAPI.NAVER_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static NaverRetrofit getApiClient() {
        return getInstance().create(NaverRetrofit.class);
    }

}
