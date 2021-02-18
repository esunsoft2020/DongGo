package com.esunsoft2020.donggo;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("/Retrofit/postMember.php")
    Call<Member> postMethod(@Body Member member);

    @GET("/Retrofit/memberArray.json")
    Call<ArrayList<Member>> getMemberArray();



}
