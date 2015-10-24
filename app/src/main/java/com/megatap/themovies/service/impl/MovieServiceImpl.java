package com.megatap.themovies.service.impl;

import com.megatap.themovies.data.MovieRepository;
import com.megatap.themovies.model.MovieDetails;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.model.MoviesListWrapper;
import com.megatap.themovies.service.Callback;
import com.megatap.themovies.service.MovieService;

import javax.inject.Inject;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/18/15.
 */
public class MovieServiceImpl implements MovieService{


    MovieRepository mMovieRepository;

    @Inject
    public MovieServiceImpl(MovieRepository movieRepository) {
        mMovieRepository = movieRepository;
    }

    @Override
    public void getMovies(int page, MovieSortType movieSortType, final Callback<MoviesListWrapper> listener) {
//        mMovieRepository.getMovies(page, movieSortType, new Callback<MoviesListWrapper>() {
//            @Override
//            public void onSuccess(MoviesListWrapper response) {
//                listener.onSuccess(response);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                listener.onError(t);
//            }
//        });

        mMovieRepository.getMovies(page, movieSortType, listener);
    }

    @Override
    public void getMovieDetails(long id, final Callback<MovieDetails> listener) {
        mMovieRepository.getMovieDetails(id, new Callback<MovieDetails>() {
            @Override
            public void onSuccess(MovieDetails response) {
                listener.onSuccess(response);
            }

            @Override
            public void onError(Throwable t) {
                listener.onError(t);
            }
        });
    }
}
