package com.megatap.themovies.ui.viewmodel;


import rx.Observable;
import rx.functions.Action0;
import rx.subjects.BehaviorSubject;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 1/2/16.
 */
public class Command<T> {

    private final Observable<T> mObservable;
    private final BehaviorSubject<Boolean> mCanExecute;

    public Command(Observable<T> observable) {
        this.mObservable = observable;
        this.mCanExecute = BehaviorSubject.create(true);
    }

    public Observable<T> execute() {
        return mObservable
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mCanExecute.onNext(false);
                    }
                })
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        mCanExecute.onNext(true);
                    }
                });
    }

    public Observable<Boolean> canExecute() {
        return mCanExecute;
    }
}
