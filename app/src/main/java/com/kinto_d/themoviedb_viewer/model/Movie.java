package com.kinto_d.themoviedb_viewer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by g40 on 05/01/17.
 */

public class Movie implements Serializable {

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("adult")
    public Boolean adult;

    @SerializedName("overview")
    public String overview;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("genre_ids")
    public ArrayList<Integer> genreIds = null;

    @SerializedName("id")
    public Integer id;

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("title")
    public String title;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("popularity")
    public Double popularity;

    @SerializedName("vote_count")
    public Integer voteCount;

    @SerializedName("video")
    public Boolean video;

    @SerializedName("vote_average")
    public Double voteAverage;

    public String getShortOverview(){
        if (this.overview.length() > 100) {
            return this.overview.substring(0, 100) + "...";
        }
        return this.overview;
    }


}
