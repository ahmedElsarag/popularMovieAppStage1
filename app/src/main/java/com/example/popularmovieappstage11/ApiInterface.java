package com.example.popularmovieappstage11;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );



//    https://api.themoviedb.org/3/movie/popular?api_key=c5412317073cc559a790ea005cb08753&language=en-US&page=1
}

