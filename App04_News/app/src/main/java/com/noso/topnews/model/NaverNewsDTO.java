package com.noso.topnews.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NaverNewsDTO {

//    rss;//	-	디버그를 쉽게 하고 RSS 리더기만으로 이용할 수 있게 하기 위해 만든 RSS 포맷의 컨테이너이며 그 외의 특별한 의미는 없다.
//            channel	-	검색 결과를 포함하는 컨테이너이다. 이 안에 있는 title, link, description 등의 항목은 참고용으로 무시해도 무방하다.
//    lastBuildDate;//	datetime	검색 결과를 생성한 시간이다.
//    total;//	integer	검색 결과 문서의 총 개수를 의미한다.
//    start;//	integer	검색 결과 문서 중, 문서의 시작점을 의미한다.
//    display;//	integer	검색된 검색 결과의 개수이다.
//    item/items;//	-	XML 포멧에서는 item 태그로, JSON 포멧에서는 items 속성으로 표현된다. 개별 검색 결과이며 title, originallink, link, description, pubDate를 포함한다.
    private String title;//	string	개별 검색 결과이며, title, originallink, link, description, pubDate 를 포함한다.
    private String originallink;//	string	검색 결과 문서의 제공 언론사 하이퍼텍스트 link를 나타낸다.
    private String link;//	string	검색 결과 문서의 제공 네이버 하이퍼텍스트 link를 나타낸다.
    private String description;//	string	검색 결과 문서의 내용을 요약한 패시지 정보이다. 문서 전체의 내용은 link를 따라가면 읽을 수 있다. 패시지에서 검색어와 일치하는 부분은 태그로 감싸져 있다.
    private String pubDate;//	datetime	검색 결과 문서가 네이버에 제공된 시간이다.
}
