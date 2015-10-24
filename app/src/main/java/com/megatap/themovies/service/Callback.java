package com.megatap.themovies.service;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/18/15.
 */
public interface Callback<T> {

    void onSuccess(T response);
    void onError(Throwable t);
}
