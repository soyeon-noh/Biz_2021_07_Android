package com.noso.movie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;

import com.noso.movie.databinding.ActivityMainBinding;
import com.noso.movie.service.NaverMovieService;
import com.noso.movie.service.impl.NaverMovieServiceImplV1;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding main_binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        main_binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(main_binding.getRoot());

        NaverMovieService naverMovieService
                = new NaverMovieServiceImplV1(main_binding.movieListView);
        naverMovieService.getMovie("모가디슈");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("영화 검색");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("검색 버튼 클릭", query);

                NaverMovieService naverMovieService = new NaverMovieServiceImplV1(main_binding.movieListView);
                naverMovieService.getMovie(query.trim());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("현재 입력하는 문자열 : ", newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}