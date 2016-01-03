package com.megatap.themovies.service.impl;

import com.megatap.themovies.model.User;
import com.megatap.themovies.service.UserService;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 1/2/16.
 */
public class UserServiceImpl implements UserService {

    @Override
    public Observable<User> signIn(final String email, String password) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(final Subscriber<? super User> subscriber) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            if (email.equals("test@example.com")) {
                                User authenticatedUser = new User();
//                                authenticatedUser.setEmail("test@example.com");
//                                authenticatedUser.setAuthenticated(true);
                                subscriber.onNext(authenticatedUser);
                                subscriber.onCompleted();
                            } else {
                                subscriber.onError(new Exception("Invalid username or password"));
                            }
                        } catch (InterruptedException e) {
                            subscriber.onError(e);
                        }
                    }
                }).start();
            }
        });
    }
}
