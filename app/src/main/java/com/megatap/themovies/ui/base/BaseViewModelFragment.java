package com.megatap.themovies.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 11/12/15.
 */
public abstract class BaseViewModelFragment extends Fragment {

    private static final String EXTRA_VIEW_MODEL_STATE = "extra_view_model_state";

    private BaseViewModel mBaseViewModel;

    @Nullable
    protected abstract BaseViewModel createViewModel(@Nullable BaseViewModel.State savedViewModelState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseViewModel.State savedViewModelState = null;
        if (savedInstanceState != null) {
            savedViewModelState = savedInstanceState.getParcelable(EXTRA_VIEW_MODEL_STATE);
        }
        mBaseViewModel = createViewModel(savedViewModelState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBaseViewModel != null) {
            mBaseViewModel.onStart();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBaseViewModel != null) {
            outState.putParcelable(EXTRA_VIEW_MODEL_STATE, mBaseViewModel.getInstanceState());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBaseViewModel != null) {
            mBaseViewModel.onStop();
        }
    }
}
