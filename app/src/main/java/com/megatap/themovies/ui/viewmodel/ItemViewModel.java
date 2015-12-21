package com.megatap.themovies.ui.viewmodel;

import com.megatap.themovies.ui.base.BaseViewModel;

/**
 * Created by <tinh.nguyen@tapptic.com> on 11/13/15.
 */
public abstract class ItemViewModel<ITEM_T> extends BaseViewModel {

    public ItemViewModel() {
        super(null);
    }

    public abstract void setItem(ITEM_T item);
}