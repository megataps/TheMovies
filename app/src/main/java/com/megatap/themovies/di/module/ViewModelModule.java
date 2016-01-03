package com.megatap.themovies.di.module;

import com.megatap.themovies.di.scope.ApplicationScope;
import com.megatap.themovies.service.UserService;
import com.megatap.themovies.ui.viewmodel.LoginViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 1/3/16.
 */
@Module(includes = ServiceModule.class)
public class ViewModelModule {

    @Provides
    @ApplicationScope
    LoginViewModel provideLoginViewModel(UserService userService) {
        return new LoginViewModel(userService);
    }
}
