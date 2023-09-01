package com.example.medic.api_and_model;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Retrofit_instance {
private static Retrofit retrofit=null;
private static final String baseURL="https://newsapi.org/v2/";

public static Retrofit getRetrofit()
{
    if(retrofit==null)
        retrofit=new Retrofit
                .Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    return retrofit;
}
}
