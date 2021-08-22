package com.noso.movie.service.impl;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.noso.movie.adapter.MovieViewAdapter;
import com.noso.movie.config.Naver_API;
import com.noso.movie.model.NaverMovieDTO;
import com.noso.movie.model.NaverParent;
import com.noso.movie.service.NaverMovieService;
import com.noso.movie.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMovieServiceImplV1 implements NaverMovieService {
    protected RecyclerView recyclerView;
    public NaverMovieServiceImplV1(RecyclerView movieListView) {
        this.recyclerView = recyclerView;
    }

    @Override
    public NaverMovieDTO getMovie(String search) {

        // 이벤트를 설정할 대상
        Call<NaverParent> retrofitReturn = RetrofitClient.getApiClient()
                .getMovie(
                        Naver_API.NAVER_CLIENT_ID,
                        Naver_API.NAVER_CLIENT_SECRET,
                        search,
                        1,
                        10);

        retrofitReturn.enqueue(new Callback<NaverParent>() {
            @Override
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {

                int resCode = response.code();
                if (resCode < 300) {
                    Log.d("영화정", response.body().toString());

                    List<NaverMovieDTO> movieList = response.body().items;
                    MovieViewAdapter movieAdapter = new MovieViewAdapter(movieList);

                    recyclerView.setAdapter(movieAdapter);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(layoutManager);

                } else {
                    Log.d("Error 발생!!!", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NaverParent> call, Throwable t) {

            }
        });

        return null;
    }
}
