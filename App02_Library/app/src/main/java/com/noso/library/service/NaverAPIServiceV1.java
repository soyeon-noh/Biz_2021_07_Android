package com.noso.library.service;

// Ctrl + Alt + O : import 정리
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.noso.library.adapter.BookAdapter;
import com.noso.library.config.Naver;
import com.noso.library.databinding.FragmentFirstBinding;
import com.noso.library.model.BookDTO;
import com.noso.library.model.NaverParent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverAPIServiceV1 extends Thread {

    private BookAdapter bookAdapter = null;
    private FragmentFirstBinding binding = null;

    public NaverAPIServiceV1(BookAdapter bookAdapter, FragmentFirstBinding binding) {
        this.bookAdapter = bookAdapter;
        this.binding = binding;
    }

    // 3. 생성된 Connection을 통하여 데이터를 가져오고
    //    필요한 데이터를 parsing하여 books 객체에 담기
    public void getNaverBooks(String search) {

        // 아래의 코드를 실행하면 Retrofit 설정된 값을 기준으로
        // naver에 요청을 수행한다.
        // 이때 이 코드는 비동기 방식으로 작동이 된다.
        Call<NaverParent> naverCall = RetrofitClient.getClient()
                .getNaverBook(
                        Naver.CLIENT_ID,
                        Naver.CLIENT_SECRET,
                        search,
                        50,
                        1);

        /*
         * Retrofit 은 API요청을 비동기 방식으로 수행한다.
         * 일반적으로 Network나 외부 장치와 데이터를 주고 받을때느
         * 대부분의 코드를 비동기, 또는 thread 방식으로 사용한다.
         *
         * 동기방식
         * 요청수행 ==> 결과가 return 되어 올때까지 대기
         *
         * 비동기방식
         * 요청수행 ==> 결과가 return 되든 말든 다른일 수행하기
         * 결과 return되면 그 결과가 수신하여 처리할 event handler를 작성해야한다.
         */

        // Retrofit event 핸들러 작성
        naverCall.enqueue(new Callback<NaverParent>() {

            private NaverParent naverParent;

            @Override // 데이터가 응답되었다!
            public void onResponse(Call<NaverParent> call, Response<NaverParent> response) {
                // return된 response 객체 확인
                Log.d("Naver Res Return", response.toString());
                int resCode = response.code();

                if (resCode < 300) { // 정상적인 코드가 왔을때.  300미만일때만 검사하면된다
                    // 실제 데이터만 추출
                    naverParent = response.body();
                    Log.d("Naver Return", naverParent.toString());

                    List<BookDTO> bookDTOList = naverParent.items;
                    bookAdapter = new BookAdapter(bookDTOList);
                    binding.bookListView.setAdapter(bookAdapter);

                    RecyclerView.LayoutManager layoutManager
                            = new LinearLayoutManager(binding.getRoot().getContext());
                    binding.bookListView.setLayoutManager(layoutManager);


                }
            }

            @Override // 실패했다..!
            public void onFailure(Call<NaverParent> call, Throwable t) {
                Log.d("오류가 발생했음", t.toString());
            }
        });
    }
}
