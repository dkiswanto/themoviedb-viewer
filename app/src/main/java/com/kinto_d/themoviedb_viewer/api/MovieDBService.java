package com.kinto_d.themoviedb_viewer.api;

import com.kinto_d.themoviedb_viewer.model.ResponseMovie;
import com.kinto_d.themoviedb_viewer.model.ResponseReview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by g40 on 05/01/17.
 */

public interface MovieDBService {
    @GET("3/discover/movie")
    Call<ResponseMovie> listMovie(@Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}")
    Call<ResponseMovie> detailMovie(@Path("movie_id") String movieID, @Query("api_key") String apiKey);

    @GET("3/movie/{movie_id}/reviews")
    Call<ResponseReview> reviewMovie(@Path("movie_id") String movieID, @Query("api_key") String apiKey);

}



//[The Movie DB Api Endpoint]
//Api Key : 1b2f29d43bf2e4f3142530bc6929d341 (Buat yang males bikin)
//
//List Movie : http://api.themoviedb.org/3/discover/movie?api_key=key
//Detail Movie : http://api.themoviedb.org/3/movie/:movie_id?api_key=key
//
//Poster Image URL
//https://image.tmdb.org/t/p/w185/{{ image_url }}
//https://image.tmdb.org/t/p/w500/{{ image_url }}
//
//Backdrop Image
//https://image.tmdb.org/t/p/w780/{{ backdrop_url }}
//
//Official Doc
//https://developers.themoviedb.org/3
