package com.noso.movies.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.noso.movies.adapter.NaverMovieAdapter;
import com.noso.movies.config.NaverAPI;
import com.noso.movies.databinding.FragmentSecondBinding;
import com.noso.movies.model.MovieDTO;
import com.noso.movies.model.NaverParent;
import com.noso.movies.service.NaverAPIService;
import com.noso.movies.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverAPIService {

    private NaverMovieAdapter adapter;
    private FragmentSecondBinding secondBinding;

    // 생성자를 binding만 선택하고 ok
    public NaverMovieServiceImplV1(FragmentSecondBinding secondBinding) {
        this.secondBinding = secondBinding;
    }

    @Override
    public void getNaverMovie(String search) {
        /**
         * Naver에 API 조회를 수행하기 위한 객체를 생성하기
         */
        Call<NaverParent> naverCall = RetrofitClient.getApiClient()
                .getMovies(
                        NaverAPI.NAVER_CLIENT_ID,
                        NaverAPI.NAVER_CLIENT_SECRET,
                        search, 1, 20);

        /**
         * 생성된 API 객체에 대하여 비동기 Call method를 선언하기
         *  Retrofit이 naver에 API 요청을 하고
         *  API 결과가 다다르면(도착하면) 반응하는 mehtod
         */
        naverCall.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                Log.d("Naver 영화정보", response.toString());
                int resCode = response.code();
                if(resCode < 300) {
                    NaverParent naverParent = response.body();
                    Log.d("네이버에서 받은 데이터", naverParent.toString());

                    List<MovieDTO> movieList = naverParent.items;
                    adapter = new NaverMovieAdapter(movieList);
                    secondBinding.movieListView.setAdapter(adapter);

                    RecyclerView.LayoutManager
                            layoutManager
                            = new LinearLayoutManager(secondBinding.getRoot().getContext());
                    secondBinding.movieListView.setLayoutManager(layoutManager);

                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });

    }
}
