package com.example.myweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Retrofit_interface {

    @GET
    Call<WeatherModel> getWeatherDetails(@Url String url);

}
