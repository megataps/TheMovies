package com.megatap.themovies.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.megatap.themovies.R;
import com.megatap.themovies.di.component.BaseComponent;
import com.megatap.themovies.model.Genre;
import com.megatap.themovies.model.MovieDetail;
import com.megatap.themovies.service.Callback;
import com.megatap.themovies.service.MovieService;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 11/5/15.
 */
public class MovieDetailsFragment extends BaseFragment{

    private static final String TAG = MovieDetailsFragment.class.getName();

    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

    @Inject
    MovieService mMovieService;

    private long mMovieId;
    private MovieDetail mMovieDetails;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Bind(R.id.content_image)
    ImageView mMovieImageView;

    @Bind(R.id.content_description)
    TextView mDescriptionTextView;

    @Bind(R.id.content_rating)
    TextView mVoteRatingTextView;

    @Bind(R.id.content_genre)
    TextView mGenreTextView;

    @Bind(R.id.content_homepage)
    TextView mHomePageTextView;

    @Bind(R.id.content_date)
    TextView mDateTextView;

    @Bind(R.id.content_popularity)
    TextView mPopulrityTextView;

    @Bind(R.id.container_homepage)
    CardView mHomepageContainerView;


    public static BaseFragment newInstance(long movieId) {
        Bundle b = new Bundle();
        b.putLong(ARG_MOVIE_ID, movieId);
        MovieDetailsFragment f = new MovieDetailsFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        setHasOptionsMenu(true);

        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(ARG_MOVIE_ID)) {
                mMovieId = args.getLong(ARG_MOVIE_ID);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().finish();
            getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareUi(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareData();
    }

    @Override
    protected void setupComponent(BaseComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected void prepareUi(@NonNull View view) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void prepareData() {
        Log.e(TAG, "prepareData");

        Log.e(TAG, "mMovieId:" + mMovieId);

        mMovieService.getMovieDetails(mMovieId, new Callback<MovieDetail>() {
            @Override
            public void onSuccess(MovieDetail response) {
                bindData(response);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void bindData(MovieDetail data) {
        mMovieDetails = data;

        collapsingToolbar.setTitle(data.getOriginalTitle());
        mDescriptionTextView.setText(data.getOverview());

        StringBuilder builder = new StringBuilder();
        for (Genre genre : data.getGenres()) {
            if (!TextUtils.isEmpty(builder)) {
                builder.append(", ");
            }
            builder.append(genre.getName());
        }

        mGenreTextView.setText(builder.toString());

        String homepage = data.getHomepage();
        if (!TextUtils.isEmpty(homepage)) {
            mHomePageTextView.setText(data.getHomepage());
            mHomepageContainerView.setVisibility(View.VISIBLE);
        }

        Date date = data.getReleaseDate();
        if (date != null) {
            CharSequence dateSequence = DateUtils.getRelativeTimeSpanString(date.getTime());
            mDateTextView.setText(dateSequence);
        }

        mVoteRatingTextView.setText(String.valueOf(data.getVoteAverage()));
        mPopulrityTextView.setText(String.format("%.3f", data.getPopularity()));

        String imageUrl = getString(R.string.backend_images_thumb_base_url) + data.getPosterPath();
        Glide.with(MovieDetailsFragment.this).load(imageUrl).centerCrop().into(mMovieImageView);
    }
}
