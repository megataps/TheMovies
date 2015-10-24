package com.megatap.themovies.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/15/15.
 */
@Module
public class ApplicationModule {

    private static Application sApplication;

    public ApplicationModule(Application application) {
        sApplication = application;
    }

    @Singleton
    @Provides
    Application providesApplication(){
        return sApplication;
    }

}