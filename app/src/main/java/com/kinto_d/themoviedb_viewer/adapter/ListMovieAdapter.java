package com.kinto_d.themoviedb_viewer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kinto_d.themoviedb_viewer.R;
import com.kinto_d.themoviedb_viewer.api.MovieDBApi;
import com.kinto_d.themoviedb_viewer.model.Movie;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by g40 on 05/01/17.
 */

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> listMovies;
    private OnItemClickListener itemClickListener;

    public ListMovieAdapter(ArrayList<Movie> listMovies){
        this.listMovies = listMovies;
    }

    public void setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_movies, parent, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        // Set Listener
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        holder.title.setText(listMovies.get(position).title);
        holder.description.setText(listMovies.get(position).getShortOverview());
        holder.rating.setRating(listMovies.get(position).voteAverage.floatValue() / 2);
        Glide
            .with(holder.poster.getContext())
            .load(MovieDBApi.getThumbnailUrl(listMovies.get(position).posterPath))
            .centerCrop()
            .placeholder(R.drawable.ic_photo_black_24dp)
            .crossFade()
            .into(holder.poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(listMovies.get(position));
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClickListener.onItemLongClick(position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    // Inner Class View Holder
    public class MovieViewHolder extends RecyclerView.ViewHolder{

        ImageView poster;
        TextView title;
        TextView description;
        RatingBar rating;

        public MovieViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.image_movies_poster);
            title = (TextView) itemView.findViewById(R.id.text_movies_title);
            description = (TextView) itemView.findViewById(R.id.text_movies_description);
            rating = (RatingBar) itemView.findViewById(R.id.rating_movies);
            rating.setMax(5);

        }
    }

}
