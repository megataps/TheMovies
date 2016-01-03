package com.megatap.themovies.service;

import com.megatap.themovies.model.MovieDetail;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.model.MoviesListWrapper;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/18/15.
 */
public interface MovieService {

    void getMovies(int page, MovieSortType movieSortType, final Callback<MoviesListWrapper> listener);

    void getMovieDetails(long id, final Callback<MovieDetail> listener);

}
