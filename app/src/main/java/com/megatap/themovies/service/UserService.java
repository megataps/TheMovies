package com.megatap.themovies.service;

import com.megatap.themovies.model.User;

import rx.Observable;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 1/2/16.
 */
public interface UserService {

    Observable<User> signIn(String email, String password);

}
