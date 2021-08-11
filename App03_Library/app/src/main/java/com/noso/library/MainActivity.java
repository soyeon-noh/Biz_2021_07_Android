package com.noso.library;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SearchView;

import com.noso.library.service.NaverBookService;
import com.noso.library.service.impl.NaverBookServiceImplV1;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    /**
     * Life Cycle
     * 폰에서 아이콘을 터치하여 App을 실행하고
     * App을 사용하고
     * App을 종료하는 과정
     *
     * And OS가 하는일
     *  App을 실행했을때
     *  Android 가 폰에서 어플을 읽어서
     *  메모리에 load하고
     *  화면 구성요서를 읽어서 화면을 그리고
     *         onCreate**() method 내에 관련된 코드 실행행
    *  각 기능을 사용하기 위한 여러가지 event handler를 등록
     *
     *
     */

    @Override
    // life cycle에서 startUp Method, start point method
    // App이 실행될때 가장 먼저 호출되어 코드가 실행되는 method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar main_toolbar = findViewById(R.id.main_app_toolbar);
        setSupportActionBar(main_toolbar);


        recyclerView = findViewById(R.id.book_list_view);
        NaverBookService naverBookService
            = new NaverBookServiceImplV1(recyclerView);
        naverBookService.getBooks("자바");
    }

    /**
     * res/menu/menu.xml 파일을 읽어서 화면의
     * ActionBar에 메뉴 등을 표현할때 사용하는 method
     *
     * 이 method는 Android가 App을 화면에 띄울때
     * 자동으로 호출하여사용하는 method
     *
     * activity.xml 파일에 toolbar관련 항목이 있으면
     * Android는 MainActivity에 onCreateOptionsMenu() 가 있는지 확인하고
     * 있으면 해당 method를 호출하여 실행한다.
     *
     * ActionBar에 보여줄 여러가지 항목이나, 기능등을 설정(선언)을
     * 여기에서 수행하면 된다.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // 매개변수로 받은 menu는
        // toolbar에 기본으로 포함된 아무것도 없는 상태의 menu 객체

        // 현재 toolbar에 기본으로 포함된 blank menu 객체에
        // main_toolbar_menu.xml 파일에 작성되어 있는 item 요소를 추가하여
        // toolbar에 menu가 나타나도록 하여라
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);


        /**
         * ActionBar(AppBar)에 설정된 search 기능 구현하기
         * 1. SearchView 객체 생성하기
         */

        SearchView searchView
                = (SearchView) menu
                .findItem(R.id.app_bar_search) // search 뷰를 가져와서
                .getActionView(); // 액션뷰를 활성화시켜라, 객체화시켜라

        /**
         * Integer.MAX_VALUE
         * Java에서 표현할 수 있는 정수의 최대값
         */
        // SearchBar width를 최대값으로 설정하여 화며에 가득차도록
        searchView.setMaxWidth(Integer.MAX_VALUE);


        /**
         * 검색창에 값이 입력되고,
         * 키보드의 돋보기(검색)을 클릭했을때
         * 반응할 event 설정하기
         */

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 검색창에 문자열을 입력하고
            // 키보드 검색(돋보기)버튼을 클릭했을때 반응
            @Override
            public boolean onQueryTextSubmit(String query) { // 키보드에 있는 검색버튼을 클릭했을때
                Log.d("검색버튼 클릭 : ", query);

                NaverBookService naverBookService
                        = new NaverBookServiceImplV1(recyclerView);
                naverBookService.getBooks(query.trim());

                return false;
            }

            // 검색창에 문자열을 입력할때 반응하는 method
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("현재 입력한 문자열: ", newText);
                return false;

            }
        });



        return super.onCreateOptionsMenu(menu);
    }
}