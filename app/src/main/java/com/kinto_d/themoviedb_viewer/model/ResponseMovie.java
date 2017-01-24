package com.kinto_d.themoviedb_viewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by g40 on 05/01/17.
 */

public class ResponseMovie {

    @SerializedName("page")
    public Integer page;

    @SerializedName("results")
    public ArrayList<Movie> results = null;

    @SerializedName("total_results")
    public Integer totalResults;

    @SerializedName("total_pages")
    public Integer totalPages;

}
