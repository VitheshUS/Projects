package com.example.medic.api_and_model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
public interface Retrofit_interface {
    @GET
    Call<NewsModel> getNews(@Url String url);
}
