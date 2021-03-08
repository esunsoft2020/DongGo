package com.esunsoft2020.donggo;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
            @Field("currentEmail") String currentEmail,
            @Field("newEmail") String newEmail
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_updateIsGosu.php")
    Call<String> getUserisGosu(
            @Field("email") String email,
            @Field("isGosu") String isGosu
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_registerWithdrawExcuse.php")
    Call<String> insertExcuse(
            @Field("email") String email,
            @Field("selectedExcuse") String selectedExcuse,
            @Field("excuse") String excuse
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_deleteAccount.php")
    Call<String> deleteAccount(
            @Field("email") String email,
            @Field("name") String name
    );

    @FormUrlEncoded
    @POST("Retrofit/retrofit_registerGosu.php")
    Call<String> getUserRegistGosuInfo(
            @Field("email") String email,
            @Field("gosuBranch") String gosuBranch,
            @Field("gosuService") String gosuService,
            @Field("serviceDetail") String serviceDetail,
            @Field("address") String address,
            @Field("radius") String radius,
            @Field("mf") String mf,
            @Field("phone") String phone
    );

    //https://youngest-programming.tistory.com/340 참고
    @Multipart
    @POST("/Retrofit/retrofit_updateProfileImgUrl.php")
    Call<String> uploadImage(
            @Part("email")String email,
            @Part MultipartBody.Part file
    );

}



