package com.megatap.themovies.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.megatap.themovies.R;
import com.megatap.themovies.di.component.BaseComponent;
import com.megatap.themovies.model.User;
import com.megatap.themovies.ui.viewmodel.LoginViewModel;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 12/22/15.
 */
public class LoginActivity extends BaseActivity {

    @Inject LoginViewModel mLoginViewModel;
    @Bind(R.id.email) EditText emailText;
    @Bind(R.id.password) EditText passwordText;
    @Bind(R.id.login) Button signInButton;
    @Bind(R.id.error_message) TextView errorMessageText;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUi();
    }

    @Override
    protected void initUi() {

        RxTextView.textChangeEvents(emailText)
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        mLoginViewModel.setEmail(textViewTextChangeEvent.text().toString());
                    }
                });

        RxTextView.textChangeEvents(passwordText)
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        mLoginViewModel.setPassword(textViewTextChangeEvent.text().toString());
                    }
                });

        mLoginViewModel.canSignIn()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean canSignIn) {
                        signInButton.setEnabled(canSignIn);
                    }
                });

        mLoginViewModel.onError()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String errorMessage) {
                        errorMessageText.setText(errorMessage);
                    }
                });

        mLoginViewModel.onSignedIn()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }

    @OnClick(R.id.login)
    void onLoginClicked() {
        mLoginViewModel.signIn();
    }
}
