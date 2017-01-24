package com.kinto_d.themoviedb_viewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by g40 on 06/01/17.
 */

public class Review {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("url")
    @Expose
    public String url;
}
