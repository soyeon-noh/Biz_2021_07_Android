package com.noso.library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.noso.library.service.NaverBookService;
import com.noso.library.service.impl.NaverBookServiceImplV1;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        recyclerView = findViewById(R.id.book_list_view);

        NaverBookService naverBookService
            = new NaverBookServiceImplV1();
        naverBookService.getBooks("자바");
    }
}