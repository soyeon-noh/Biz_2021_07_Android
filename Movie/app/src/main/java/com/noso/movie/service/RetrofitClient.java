package com.noso.movie.service;

import com.noso.movie.config.Naver_API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    // 연결 Connection 생성
    private static Retrofit getInstance() {
        return new Retrofit.Builder()
                .baseUrl(Naver_API.NAVER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Naver API 연결 Connection을 생성하고
    // DTO Mapper를 만들어 return 하기
    public static NaverRetrofit getApiClient() {
        return getInstance().create(NaverRetrofit.class);
    }
}
