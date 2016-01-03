package com.megatap.themovies.data.impl;

import com.megatap.themovies.data.MovieRepository;
import com.megatap.themovies.data.MoviesApiService;
import com.megatap.themovies.model.MovieDetail;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.model.MoviesListWrapper;
import com.megatap.themovies.service.Callback;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/19/15.
 */
public class MovieRepositoryImpl implements MovieRepository{

    public static final String API_KEY = "51e46f7d35bb269e89098306bc03c476";

    MoviesApiService mApiService;

    @Inject
    public MovieRepositoryImpl(MoviesApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void getMovies(int page, MovieSortType movieSortType, final Callback<MoviesListWrapper> listener) {
        Call<MoviesListWrapper> callResponse = mApiService.getMoviesList(movieSortType.name().toLowerCase(), page, API_KEY);

        callResponse.enqueue(new retrofit.Callback<MoviesListWrapper>() {
            @Override
            public void onResponse(Response<MoviesListWrapper> response, Retrofit retrofit) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onError(t);
            }
        });
    }

    @Override
    public void getMovieDetails(long id, final Callback<MovieDetail> listener) {
        Call<MovieDetail> callResponse = mApiService.getMovieDetails(id, API_KEY);
        callResponse.enqueue(new retrofit.Callback<MovieDetail>() {
            @Override
            public void onResponse(Response<MovieDetail> response, Retrofit retrofit) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                listener.onError(t);
            }
        });
    }
}
