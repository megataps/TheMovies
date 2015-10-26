package com.megatap.themovies.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/26/15.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onUpdateUiView(T data);
}