package com.esunsoft2020.donggo;


import java.util.ArrayList;

import kotlin.jvm.JvmMultifileClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {

    @FormUrlEncoded
    @POST("Retrofit/postMember.php")
    Call<Member> login(@Field("user_email")String email,
                       @Field("user_pw")String pw);

    @FormUrlEncoded
    @POST("Retrofit/registerMember.php")
    Call<Member> register(@Field("res_name")String name,
                          @Field("res_phone")String phone,
                          @Field("res_profileImgUrl")String profileImgUrl,
                          @Field("res_isEmailLogin")boolean isEmailLogin);


}
