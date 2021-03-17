package com.esunsoft2020.donggo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInterface {
    String LOGIN_URL = "http://donggo.dothome.co.kr/";

    @FormUrlEncoded
    @POST("Retrofit/register_simplelogin.php")
    Call<String> getUserLogin(
            @Field("email") String email,
            @Field("pw") String pw
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_gosuInfoLoading.php")
    Call<String> getGosuInfo(
            @Field("email") String email,
            @Field("name") String name
    );


}
