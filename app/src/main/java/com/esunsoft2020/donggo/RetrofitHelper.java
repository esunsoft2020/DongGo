package com.esunsoft2020.donggo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHelper {

    final static String baseUrl = "http://donggo.dothome.co.kr/";


    static Retrofit getRetrofitInstance(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.addConverterFactory(ScalarsConverterFactory.create());
        Retrofit retrofit = builder.build();

        return retrofit;
    }


//    private static Retrofit retrofit;
//    public static Retrofit getApiClient()
//    {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        if (retrofit == null)
//        {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(baseUrl)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//        }
//
//        return retrofit;
//    }
}
