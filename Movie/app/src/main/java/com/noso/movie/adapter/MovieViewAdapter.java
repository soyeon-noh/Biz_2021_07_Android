package com.noso.movie.adapter;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noso.movie.databinding.MovieItemViewBinding;
import com.noso.movie.model.NaverMovieDTO;

import java.util.List;

public class MovieViewAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NaverMovieDTO> movieList;

    public MovieViewAdapter(List<NaverMovieDTO> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater
                = LayoutInflater.from(parent.getContext());

        MovieItemViewBinding moviewBinding
                = MovieItemViewBinding
                .inflate(layoutInflater,
                        parent,
                        false);

        return new NaverMovieViewHolder(moviewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NaverMovieViewHolder movieViewHolder = (NaverMovieViewHolder) holder;
        NaverMovieDTO movieDTO = movieList.get(position);
        MovieItemViewBinding movieBinding = movieViewHolder.viewBinding;

//        Spanned movieTitle
//                = Html.fromHtml(movieDTO.getTitle(),Html.FROM_HTML_MODE_LEGACY);
//        movieBinding.MovieItemTitle.setText(movieTitle);
//
//        Spanned moviePubDate
//                = Html.fromHtml(movieDTO.getTitle(),Html.FROM_HTML_MODE_LEGACY);
//        movieBinding.MovieItemTitle.setText(moviePubDate);
//
//        Spanned movieDirector
//                = Html.fromHtml(movieDTO.getTitle(),Html.FROM_HTML_MODE_LEGACY);
//        movieBinding.MovieItemTitle.setText(movieDirector);
//
//        Spanned movieActor
//                = Html.fromHtml(movieDTO.getTitle(),Html.FROM_HTML_MODE_LEGACY);
//        movieBinding.MovieItemTitle.setText(movieActor);


        Spanned sTitle
                = Html.fromHtml(movieDTO.getTitle(),Html.FROM_HTML_MODE_LEGACY);
        movieBinding.MovieItemTitle.setText(sTitle);

        Spanned sPubDate
                = Html.fromHtml(movieDTO.getPubDate(),Html.FROM_HTML_MODE_LEGACY);
        movieBinding.MovieItemPubDate.setText(sPubDate);

        String strDirect
                = String.format("<b>감독 : </b> %s", movieDTO.getDirector());
        Spanned sDirect
                = Html.fromHtml(strDirect,Html.FROM_HTML_MODE_LEGACY);
        movieBinding.MovieItemDirector.setText(sDirect);

        String strActor
                = String.format("<b>출연 : </b>", movieDTO.getActor());
        Spanned sActor
                = Html.fromHtml(strActor, Html.FROM_HTML_MODE_LEGACY);
        movieBinding.MovieItemActor.setText(sActor);

        Double intRating
                = Double.valueOf(movieDTO.getUserRating());
        String strRating = String.format(
                "<b>평점:</b> %3.2f",intRating);
        Spanned sRating = Html.fromHtml(strRating,Html.FROM_HTML_MODE_LEGACY);
        movieBinding.MovieItemUserRating.setText(sRating);


    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    public static class NaverMovieViewHolder extends RecyclerView.ViewHolder{

        public MovieItemViewBinding viewBinding;

        public NaverMovieViewHolder(@NonNull MovieItemViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
        }
    }
}
