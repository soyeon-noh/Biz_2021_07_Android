package com.noso.library.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Retrofit 을 사용하여 API 접속을 할때
 * 최종 end point, header값, method type, parameter 등을
 * 설정하는 인터페이스
 */
public interface NaverRetrofit {

    /**
     * method의 return type을 void 형식이 아닌
     * Call 클래스 type으로 설정 (retrofit2.Call)
     */

    // 1. 가장 기본 타입, 아무일도 할 수 없다.
    public Call getBook();

    /*
     * 2. naver openAPI를 사용하기 위해서는
     * 반드시 header에 Client ID와 Client Secret값을 전달해주어야 한다.
     * header에 Client ID와 Client Secret를 전달하기 위하여
     *      가. method의 매개변수에 해당 속성을 지정해준다.
     *      나. @Header() annotation을 부착한다. (retrofit2.http.Header)
     *      다. @Header() annotation에 Header의 이름을 지정한다.
     *          'X-Naver-Client-Id=akjfeiwfjadicj' 형식으로 요청한다.
     *
     * 3. method에 end point와 method type을 지정한다
     *      요청 method type : @GET, @POST, @PUT, @DELETE
     *
     * 4. openAPI를 통해 데이터를 요청할때
     *      값, 변수 등을 보내고 싶을때
     *      queryString 을 반드는데
     *      여기에서는 @Query() 를 사용하여 지정한다.
     *
     * 5. return type인 Call 클래스에
     *      Data를 mapping할 DTO(VO) 클래스를
     *      Generic으로 설정해 준다.
    */
    @GET("book.json")
    public Call getBook(
            @Header("X-Naver-Client-Id") String clientId, // 대소문자 꼭 똑같이 해줘야한다! 언더바 아님!!
            @Header("X-Naver-Client-Secret") String clientSecret,
            @Query("query") String query,
            @Query("start") int start,
            @Query("display") int display
    );


}
