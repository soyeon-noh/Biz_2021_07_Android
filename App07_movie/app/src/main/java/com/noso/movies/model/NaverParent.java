package com.noso.movies.model;

import java.util.List;

import lombok.ToString;

@ToString
public class NaverParent {

    public String rss; // 디버그를 쉽게 하고 RSS 리더기만으로 이용할 수 있게 하기 위해 만든 RSS 포맷의 컨테이너
    public String channel; // 검색 결과를 포함하는 컨테이너
    public String lastBuildDate; // 검색 결과를 생성한 시간
    public List<MovieDTO> items; // XML 포멧에서는 item 태그로, JSON 포멧에서는 items 속성으로 표현된다.

}
