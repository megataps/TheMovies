package com.megatap.themovies.ui.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by <tinh.nguyen@tapptic.com> on 11/13/15.
 */
public class ViewModelBindings {

    @BindingAdapter("recyclerViewViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                RecyclerViewViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }
}
