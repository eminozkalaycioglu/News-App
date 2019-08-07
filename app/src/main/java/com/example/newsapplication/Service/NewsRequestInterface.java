package com.example.newsapplication.Service;


import com.example.newsapplication.JSON.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRequestInterface
{
    @GET("v2/top-headlines")
    Call<NewsResponse> getNewsAsSource(@Query("apiKey") String apiKey, @Query("sources") String sources);

    @GET("v2/top-headlines")
    Call<NewsResponse> getNewsAsCountry(@Query("apiKey") String apiKey, @Query("country") String country);



}
