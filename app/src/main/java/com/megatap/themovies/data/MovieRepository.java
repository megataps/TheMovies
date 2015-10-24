package com.megatap.themovies.data;

import com.megatap.themovies.model.MovieDetails;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.model.MoviesListWrapper;
import com.megatap.themovies.service.Callback;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/19/15.
 */
public interface MovieRepository {

    void getMovies(int page, MovieSortType movieSortType, final Callback<MoviesListWrapper> listener);

    void getMovieDetails(long id, final Callback<MovieDetails> listener);
}
