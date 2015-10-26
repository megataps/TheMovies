package com.megatap.themovies.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.megatap.themovies.R;
import com.megatap.themovies.model.Movie;
import com.megatap.themovies.ui.adapter.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/26/15.
 */
public class MovieListAdapter extends BaseRecycleViewAdapter {

    private final List<Movie> mDataSource = new ArrayList<>();

    public void addData(@NonNull List<Movie> data) {
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(@NonNull List<Movie> data) {
        mDataSource.clear();
        mDataSource.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder)holder).onUpdateUiView(mDataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    private static class MovieViewHolder extends BaseViewHolder<Movie> {

        private final AppCompatTextView mMovieTitleTextView;
        private final AppCompatTextView mMovieDateTextView;
        private final ImageView mMovieImageView;

        public MovieViewHolder(View view) {
            super(view);

            mMovieTitleTextView = (AppCompatTextView) view.findViewById(R.id.content_title);
            mMovieDateTextView = (AppCompatTextView) view.findViewById(R.id.content_date);
            mMovieImageView = (ImageView) view.findViewById(R.id.content_image);
        }

        @Override
        public void onUpdateUiView(Movie movie) {

            mMovieTitleTextView.setText(movie.getOriginalTitle());

            Date date = movie.getReleaseDate();
            if (date != null) {
                CharSequence dateSequence = DateUtils.getRelativeTimeSpanString(date.getTime());
                mMovieDateTextView.setText(dateSequence);
            }

            String imageUrl = itemView.getContext().getString(R.string.backend_images_thumb_base_url) + movie.getPosterPath();
            Glide.with(itemView.getContext()).load(imageUrl).centerCrop().into(mMovieImageView);
        }
    }
}
