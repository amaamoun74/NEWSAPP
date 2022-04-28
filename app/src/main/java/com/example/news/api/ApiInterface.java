package com.example.news.api;
import com.example.news.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    // to get all news
    @GET("v2/top-headlines")
    Call<News> getAllNews(@Query("country") String country, @Query("apiKey") String apiKey);


    // for specific category
    @GET("v2/top-headlines")
    Call<News> getCategorizedNews(
            @Query("country") String country,
            @Query("category") String catName,
            @Query("apiKey") String apiKey
    );


    // to search for news
    @GET("v2/everything")
    Call<News> getBySearch(
            @Query("q") String keyword,
            @Query("By") String sortBy,
            @Query("apiKey") String apiKey
    );

    // categoryURL -> "https://newsapi.org/v2/top-headlines?country="+countryName+"&category="+categoryName+"&apiKey="+api_key;
    // fullURl -> "https://newsapi.org/v2/top-headlines?country=eg&apiKey=791dc72a7bca411b9b212cfcd64e7b4f";
}
