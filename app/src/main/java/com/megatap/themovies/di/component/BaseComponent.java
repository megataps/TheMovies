package com.megatap.themovies.di.component;

import com.megatap.themovies.MainActivity;
import com.megatap.themovies.di.module.NetworkModule;
import com.megatap.themovies.di.module.ServiceModule;
import com.megatap.themovies.di.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/15/15.
 */
// AppModule [Application] |AppComponent| <- BaseComponent needs that stuff..
@ApplicationScope
@Component(
        modules = {
                ServiceModule.class,
                NetworkModule.class},
        dependencies = ApplicationComponent.class)
public interface BaseComponent {

//    MoviesApiService getMoviesApiService();
//
//    MovieService getMovieService();
//
//    OkHttpClient getOkHttpClient();
//
//    Retrofit getRetrofit();

    void inject(MainActivity Activity);
}