package com.megatap.themovies.data;

import com.megatap.themovies.model.MovieDetails;
import com.megatap.themovies.model.MoviesListWrapper;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/23/15.
 */
public interface MoviesApiService {

    @GET("3/movie/{movieType}")
    Call<MoviesListWrapper> getMoviesList(@Path("movieType") String movieType, @Query("page") int page,
                                          @Query("api_key") String apiKey);


    @GET("3/movie/{movieId}")
    Call<MovieDetails> getMovieDetails(@Path("movieId") long movieId, @Query("api_key") String apiKey);
}
