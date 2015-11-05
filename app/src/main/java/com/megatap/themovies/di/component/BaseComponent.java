package com.megatap.themovies.di.component;

import com.megatap.themovies.di.module.NetworkModule;
import com.megatap.themovies.di.module.ServiceModule;
import com.megatap.themovies.di.scope.ApplicationScope;
import com.megatap.themovies.ui.activity.MainActivity;
import com.megatap.themovies.ui.fragment.MovieDetailsFragment;
import com.megatap.themovies.ui.fragment.MovieListFragment;

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

    void inject(MovieListFragment movieListFragment);

    void inject(MovieDetailsFragment movieListFragment);

    void inject(MainActivity activity);
}