package com.kinto_d.themoviedb_viewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.kinto_d.themoviedb_viewer.adapter.ListMovieAdapter;
import com.kinto_d.themoviedb_viewer.adapter.OnItemClickListener;
import com.kinto_d.themoviedb_viewer.api.MovieDBApi;
import com.kinto_d.themoviedb_viewer.api.MovieDBService;
import com.kinto_d.themoviedb_viewer.model.Movie;
import com.kinto_d.themoviedb_viewer.model.ResponseMovie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMoviesActivity extends AppCompatActivity implements OnItemClickListener {

    private SwipeRefreshLayout mSwipeRefreshLayoutListMovie;
    private RecyclerView mRecyclerListMovie;
    private Toolbar mToolbar;
    private ListMovieAdapter mListMovieAdapter;
    private ArrayList<Movie> listMovie = new ArrayList<Movie>();

    private void initInstance(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar_list_movies);
        mSwipeRefreshLayoutListMovie = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_list_movies);
        mRecyclerListMovie = (RecyclerView) findViewById(R.id.recycler_list_movies);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movies);

        // init instance
        this.initInstance();

        setSupportActionBar(mToolbar);

        // init recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerListMovie.setLayoutManager(linearLayoutManager);
        mListMovieAdapter = new ListMovieAdapter(listMovie);
        mListMovieAdapter.setItemClickListener(this);
        mRecyclerListMovie.setAdapter(mListMovieAdapter);

        // Set Refreshing True for act like a loading status
        mSwipeRefreshLayoutListMovie.setRefreshing(true);

        requestMovieData();

        // Swipe Refresh Listener
        mSwipeRefreshLayoutListMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayoutListMovie.setRefreshing(true);
                requestMovieData();
            }
        });

    }

    @Override
    public void onItemClick(Movie movie) {
        Intent detailMovie = new Intent(this, DetailMovieActivity.class);
        detailMovie.putExtra("movie", movie);
        startActivity(detailMovie);
    }

    @Override
    public void onItemLongClick(int position) {
        listMovie.remove(position);
        mListMovieAdapter.notifyDataSetChanged();
    }


    private void requestMovieData(){

        // movie db handler
        MovieDBService movieDBService = MovieDBApi.getApi();
        Call<ResponseMovie> responseCall = movieDBService.listMovie(MovieDBApi.API_KEY);

        // call api async
        responseCall.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                listMovie.clear();
                listMovie.addAll(response.body().results);
                mListMovieAdapter.notifyDataSetChanged();
                mSwipeRefreshLayoutListMovie.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR FETCH DATA", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayoutListMovie.setRefreshing(false);
            }
        });

    }

}
