package com.kinto_d.themoviedb_viewer;

import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kinto_d.themoviedb_viewer.api.MovieDBApi;
import com.kinto_d.themoviedb_viewer.api.MovieDBService;
import com.kinto_d.themoviedb_viewer.model.Movie;
import com.kinto_d.themoviedb_viewer.model.ResponseMovie;
import com.kinto_d.themoviedb_viewer.model.ResponseReview;
import com.kinto_d.themoviedb_viewer.model.Review;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMovieActivity extends AppCompatActivity {

    @BindView(R.id.image_backdrop_movie) ImageView mMovieBackdrop;
    @BindView(R.id.toolbar_detail_movies) Toolbar mToolbar;
    @BindView(R.id.collapse_toolbar_detail_movies) CollapsingToolbarLayout mCollapsingToolbar;

    // detail view component
    @BindView(R.id.text_detail_title) TextView mDetailTitle;
    @BindView(R.id.text_detail_date_release) TextView mDateRelease;
    @BindView(R.id.text_detail_tagline) TextView mTagline;
    @BindView(R.id.text_detail_overview) TextView mOverview;
    @BindView(R.id.text_detail_review) TextView mReview;
    @BindView(R.id.image_detail_poster) ImageView mDetailPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        // Butter Knife Binding
        ButterKnife.bind(this);

        // get movie data from previous activity
        Movie movieDetail = (Movie) getIntent().getSerializableExtra("movie");

        // collapsing toolbar layout setting
        mCollapsingToolbar.setTitle(movieDetail.title);
        mCollapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Detail Movie
        mDetailTitle.setText(movieDetail.title);
        mDateRelease.setText("Release Date : " + movieDetail.releaseDate);
        mTagline.setText("TAGLINE HERE");
        mOverview.setText(movieDetail.overview);

        // set poster image & backdrop
        Glide.with(this).load(MovieDBApi.getThumbnailUrl(movieDetail.posterPath))
            .crossFade().into(mDetailPoster);

        Glide.with(this).load(MovieDBApi.getBackdropUrl(movieDetail.backdropPath))
            .crossFade().into(mMovieBackdrop);

        // get review data with retrofit
        MovieDBService movieDBService = MovieDBApi.getApi();
        Call<ResponseReview> responseCall = movieDBService.reviewMovie(String.valueOf(movieDetail.id), MovieDBApi.API_KEY);

        responseCall.enqueue(new Callback<ResponseReview>() {
            @Override
            public void onResponse(Call<ResponseReview> call, Response<ResponseReview> response) {
                if (response.body().results.size() != 0){
                    Review review = response.body().results.get(0);
                    mReview.setText(review.content);
                } else {
                    mReview.setText("REVIEW NOT AVAILABLE");
                }
            }

            @Override
            public void onFailure(Call<ResponseReview> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR FETCH DATA REVIEW", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
