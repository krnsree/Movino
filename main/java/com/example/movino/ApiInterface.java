package com.example.movino;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("3/movie/popular")
    Call<MovieResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("language")String language,
            @Query("page")int page
    );
}
