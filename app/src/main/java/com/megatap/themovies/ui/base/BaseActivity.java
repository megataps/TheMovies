package com.megatap.themovies.ui.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;

import com.megatap.themovies.TMApplication;
import com.megatap.themovies.di.component.BaseComponent;

import butterknife.ButterKnife;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/24/15.
 */
public abstract class BaseActivity extends BaseViewModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(TMApplication.getBaseComponent());

        setContentView(getLayout());

        ButterKnife.bind(this);
    }

    protected abstract int getLayout();
    protected abstract void setupComponent(BaseComponent baseComponent);
    protected abstract void initUi();

    protected void replaceFragment(@IdRes int id, @NonNull BaseFragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getTag());
        }

        transaction.commit();
    }

}
