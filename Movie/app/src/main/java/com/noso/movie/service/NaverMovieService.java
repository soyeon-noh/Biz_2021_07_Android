package com.noso.movie.service;

import com.noso.movie.model.NaverMovieDTO;

public interface NaverMovieService {
    public NaverMovieDTO getMovie(String search);
}
