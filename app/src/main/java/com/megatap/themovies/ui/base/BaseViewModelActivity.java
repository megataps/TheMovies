package com.megatap.themovies.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 11/12/15.
 */
public abstract class BaseViewModelActivity extends AppCompatActivity {

    private static final String EXTRA_VIEW_MODEL_STATE = "extra_view_model_state";

    private BaseViewModel mBaseViewModel;

    @Nullable
    protected abstract BaseViewModel createViewModel(@Nullable BaseViewModel.State savedViewModelState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseViewModel.State savedViewModelState = null;
        if (savedInstanceState != null) {
            savedViewModelState = savedInstanceState.getParcelable(EXTRA_VIEW_MODEL_STATE);
        }
        mBaseViewModel = createViewModel(savedViewModelState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBaseViewModel != null) {
            mBaseViewModel.onStart();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBaseViewModel != null) {
            outState.putParcelable(EXTRA_VIEW_MODEL_STATE, mBaseViewModel.getInstanceState());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBaseViewModel != null) {
            mBaseViewModel.onStop();
        }
    }
}
