package com.megatap.themovies.di.component;

import android.app.Application;

import com.megatap.themovies.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/15/15.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Application getApplication();
}