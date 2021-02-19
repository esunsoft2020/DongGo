package com.esunsoft2020.donggo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegisterInterface {
    String REGIST_URL = "http://donggo.dothome.co.kr/";

    @FormUrlEncoded
    @POST("Retrofit/retrofit_simpleregister.php")
    Call<String> getUserRegist(
            @Field("name") String name,
            @Field("email") String email,
            @Field("pw") String pw
    );
}



