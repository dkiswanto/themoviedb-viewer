package com.kinto_d.themoviedb_viewer.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by g40 on 05/01/17.
 */

public class MovieDBApi {

    private final static String MAIN_URL = "http://api.themoviedb.org";
    public final static String API_KEY = "1b2f29d43bf2e4f3142530bc6929d341";

    private static MovieDBService movieDBService = null;

    public static MovieDBService getApi() {
        if (movieDBService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MAIN_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            movieDBService = retrofit.create(MovieDBService.class);
        }

        return movieDBService;
    }

    public static String getThumbnailUrl(String url){
        return "https://image.tmdb.org/t/p/w185/" + url;
    }

    public static String getBackdropUrl(String url){
        return "https://image.tmdb.org/t/p/w500/" + url;
    }

    public static String getPosterUrl(String url){
        return "https://image.tmdb.org/t/p/w500/" + url;
    }



}
