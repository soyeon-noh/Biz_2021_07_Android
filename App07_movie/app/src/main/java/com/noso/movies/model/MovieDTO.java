package com.noso.movies.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String title; // 검색 결과 영화의 제목
    private String link; // 검색 결과 영화의 하이퍼텍스트 link
    private String image; // 검색 결과 영화의 썸네일 이미지의 URL
    private String subtitle; // 검색 결과 영화의 영문 제목
    private String pubDate; // 검색 결과 영화의 제작년도
    private String director; // 검색 결과 영화의 감독
    private String actor; // 검색 결과 영화의 출연 배우
    private String userRating; // 검색 결과 영화에 대한 유저들의 평점

}
