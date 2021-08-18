package com.noso.movies.service;

import android.util.Log;

import com.noso.movies.config.NaverAPI;
import com.noso.movies.model.NaverParent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverApiService {

    @Override
    public void getNaverData(String search) {

        Call<NaverParent> naverCall
                = RetrofitClient.getApiClient().getMovie(
                NaverAPI.NAVER_CLIENT_ID,
                NaverAPI.NAVER_CLIENT_SECRET,
                search,
                1,20
        );

        naverCall.enqueue(new Callback<NaverParent>() { // new하고 자동완성하면 저절로 override
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                Log.d("Response", response.toString());

                int resCode = response.code();
                if(resCode < 300){
                    NaverParent movieData = response.body();
                    Log.d("네이버에서 받은 데이터", movieData.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });

    }
}
