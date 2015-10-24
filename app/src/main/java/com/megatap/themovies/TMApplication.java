package com.megatap.themovies;

import android.app.Application;

import com.megatap.themovies.di.component.ApplicationComponent;
import com.megatap.themovies.di.component.BaseComponent;
import com.megatap.themovies.di.component.DaggerApplicationComponent;
import com.megatap.themovies.di.component.DaggerBaseComponent;
import com.megatap.themovies.di.module.ApplicationModule;
import com.megatap.themovies.di.module.NetworkModule;
import com.megatap.themovies.di.module.ServiceModule;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/19/15.
 */
public class TMApplication extends Application {

    private static BaseComponent sBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        sBaseComponent = DaggerBaseComponent.builder()
                .applicationComponent(applicationComponent)
                .networkModule(new NetworkModule())
                .serviceModule(new ServiceModule())
                .build();
    }

    public static BaseComponent getBaseComponent(){
        return sBaseComponent;
    }


}
