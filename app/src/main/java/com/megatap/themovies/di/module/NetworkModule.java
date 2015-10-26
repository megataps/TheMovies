package com.megatap.themovies.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megatap.themovies.di.scope.ApplicationScope;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/19/15.
 */
@Module
public class NetworkModule {

    private static final String KEY_BASE_URL = "http://api.themoviedb.org/";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Provides
    @ApplicationScope
    Gson provideGson() {
        return new GsonBuilder().setDateFormat(DATE_FORMAT).create();
    }

    @Provides
    @ApplicationScope
    Converter.Factory provideFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @ApplicationScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @ApplicationScope
    Interceptor provideInterceptor() {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .header("Accept", "application/json")
                        .build();

                return chain.proceed(request);
            }
        };
    }

    @Provides
    @ApplicationScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, Interceptor interceptor) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);

        okHttpClient.interceptors().add(loggingInterceptor);
        okHttpClient.interceptors().add(interceptor);

        return okHttpClient;
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Converter.Factory factory) {

        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(KEY_BASE_URL)
                .addConverterFactory(factory)
                .client(okHttpClient);


        return builder.build();
    }
}
