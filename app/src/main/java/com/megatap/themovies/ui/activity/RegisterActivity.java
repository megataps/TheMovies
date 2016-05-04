package com.megatap.themovies.ui.activity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.megatap.themovies.R;
import com.megatap.themovies.di.component.BaseComponent;

import java.util.regex.Pattern;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by <tinh.nguyen@tapptic.com> on 1/15/16.
 * https://www.ykode.com/2015/02/20/android-frp-rxjava-retrolambda.html
 */
public class RegisterActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUi();
    }

    @Override
    protected void initUi() {

        final Pattern emailPattern = Pattern.compile(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        final EditText emailEdit = (EditText) findViewById(R.id.email);
        final EditText passwordEdit = (EditText) findViewById(R.id.password);
        final Button registerButton = (Button) findViewById(R.id.register);

        Observable<Boolean> passwordValid = RxTextView.textChangeEvents(passwordEdit)
                .map(new Func1<TextViewTextChangeEvent, String>() {
                    @Override
                    public String call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return textViewTextChangeEvent.text().toString();
                    }
                })
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.length() > 6;
                    }
                });

        Observable<Boolean> emailValid = RxTextView.textChangeEvents(emailEdit)
                .map(new Func1<TextViewTextChangeEvent, String>() {
                    @Override
                    public String call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        return textViewTextChangeEvent.text().toString();
                    }
                })
                .map(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return emailPattern.matcher(s).matches();
                    }
                });

        emailValid.map(new Func1<Boolean, Integer>() {
            @Override
            public Integer call(Boolean color) {
                return color ? Color.BLACK : Color.RED;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                emailEdit.setTextColor(integer);
            }
        });

        passwordValid.map(new Func1<Boolean, Integer>() {
            @Override
            public Integer call(Boolean color) {
                return color ? Color.BLACK : Color.RED;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                passwordEdit.setTextColor(integer);
            }
        });


        Observable<Boolean> registerEnabled =
                Observable.combineLatest(emailValid, passwordValid, new Func2<Boolean, Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean aBoolean, Boolean aBoolean2) {
                        return aBoolean && aBoolean2;
                    }
                });

        registerEnabled.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                registerButton.setEnabled(aBoolean);
            }
        });


//        Observable<TextViewTextChangeEvent> userNameText =
//                RxTextView.textChangeEvents(emailEdit);
//        userNameText.filter(new Func1<TextViewTextChangeEvent, Boolean>() {
//                    @Override
//                    public Boolean call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                        return textViewTextChangeEvent.text().toString().length() > 4;
//                    }
//                })
//                .subscribe(new Action1<TextViewTextChangeEvent>() {
//            @Override
//            public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
//                Log.d("[Rx]", textViewTextChangeEvent.text().toString());
//            }
//        });
    }
}
