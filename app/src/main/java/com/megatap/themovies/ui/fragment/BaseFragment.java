package com.megatap.themovies.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.megatap.themovies.TMApplication;
import com.megatap.themovies.di.component.BaseComponent;

import butterknife.ButterKnife;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/24/15.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setupComponent(TMApplication.getBaseComponent());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    protected abstract void setupComponent(BaseComponent appComponent);

    protected abstract void prepareUi(@NonNull View view);

    protected abstract void prepareData();
}
