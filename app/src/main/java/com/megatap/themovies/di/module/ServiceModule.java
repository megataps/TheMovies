package com.megatap.themovies.di.module;

import com.megatap.themovies.data.MovieRepository;
import com.megatap.themovies.data.MoviesApiService;
import com.megatap.themovies.data.impl.MovieRepositoryImpl;
import com.megatap.themovies.di.scope.ApplicationScope;
import com.megatap.themovies.service.MovieService;
import com.megatap.themovies.service.impl.MovieServiceImpl;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 9/11/15.
 */
@Module (includes = NetworkModule.class)
public class ServiceModule {

    @ApplicationScope
    @Provides
    public MoviesApiService provideMoviesApiService(Retrofit retrofit) {
        return retrofit.create(MoviesApiService.class);
    }

    @ApplicationScope
    @Provides
    MovieRepository provideMovieRepository(MoviesApiService apiService) {
        return new MovieRepositoryImpl(apiService);
    }

    @ApplicationScope
    @Provides
    MovieService provideMovieService(MovieRepository movieRepository) {
        return new MovieServiceImpl(movieRepository);
    }
}
