package com.kinto_d.themoviedb_viewer.adapter;

import com.kinto_d.themoviedb_viewer.model.Movie;

/**
 * Created by g40 on 06/01/17.
 */

public interface OnItemClickListener {
    void onItemClick(Movie movie);
    void onItemLongClick(int position);
}
