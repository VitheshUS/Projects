package com.example.myweather;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_instance {

    private static Retrofit retrofit;
    private static final String BASEURL="https://api.weatherapi.com/v2/";

    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
