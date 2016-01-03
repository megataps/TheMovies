package com.megatap.themovies.ui.viewmodel;


import com.megatap.themovies.model.User;
import com.megatap.themovies.service.UserService;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func3;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 12/22/15.
 */
public class LoginViewModel{

    private BehaviorSubject<String> mEmailSubject;
    private BehaviorSubject<String> mPasswordSubject;
    private Observable<Boolean> mCanSignIn;
    private PublishSubject<String> mError;
    private PublishSubject<User> mSignedInSubject;
    private Command<User> mLoginCommand;
    private UserService mUserService;

    @Inject
    public LoginViewModel(UserService userService) {
        mUserService = userService;

        mEmailSubject = BehaviorSubject.create();
        mPasswordSubject = BehaviorSubject.create();
        mSignedInSubject = PublishSubject.create();
        mError = PublishSubject.create();

        mLoginCommand = new Command<>(Observable.defer(
                new Func0<Observable<User>>() {
                    @Override
                    public Observable<User> call() {
                        return mUserService.signIn(mEmailSubject.getValue(),
                                mPasswordSubject.getValue());
                    }
                }
        ));

        mCanSignIn = Observable.combineLatest(mLoginCommand.canExecute(), mEmailSubject, mPasswordSubject,
                new Func3<Boolean, String, String, Boolean>() {
                    @Override
                    public Boolean call(Boolean canExecute, String username, String password) {
                        return canExecute
                                && username != null && username.length() > 0
                                && password != null && password.length() > 0;
                    }
                });

        Observable.merge(mEmailSubject, mPasswordSubject)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String message) {
                        mError.onNext(null);
                    }
                });

        setEmail(null);
        setPassword(null);
    }

    public void setEmail(String email) {
        mEmailSubject.onNext(email);
    }

    public void setPassword(String password) {
        mPasswordSubject.onNext(password);
    }

    public void signIn() {
        mLoginCommand.execute()
                .subscribe(new Action1<User>() {
                               @Override
                               public void call(User user) {
                                   mSignedInSubject.onNext(user);
                               }
                           },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable error) {
                                mError.onNext(error.getMessage());
                            }
                        });
    }

    public Observable<User> onSignedIn() {
        return mSignedInSubject;
    }

    public Observable<Boolean> canSignIn() {
        return mCanSignIn;
    }

    public Observable<String> onError() {
        return mError;
    }
}
