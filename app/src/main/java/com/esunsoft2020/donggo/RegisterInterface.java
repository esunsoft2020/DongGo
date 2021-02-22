package com.esunsoft2020.donggo;

import kotlin.jvm.JvmMultifileClass;
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

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updateName.php")
    Call<String> getUserName(
            @Field("email") String email,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updatePhone.php")
    Call<String> getUserPhone(
            @Field("email") String email,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updatePw.php")
    Call<String> getUserPw(
            @Field("email") String email,
            @Field("currentPw") String currentPw,
            @Field("newPw") String newPw
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updateEmail.php")
    Call<String> getUserEmail(
            @Field("email") String CurrentEmail,
            @Field("email") String NewEmail
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updateProfileImgUrl.php")
    Call<String> getUserProfileImgUrl(
            @Field("email") String email,
            @Field("profileImgUrl")String profileImgUrl
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updateIsGosu.php")
    Call<String> getUserisGosu(
            @Field("email") String email,
            @Field("isGosu") String isGosu
    );
}



