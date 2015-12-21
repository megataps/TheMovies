package com.megatap.themovies.ui.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.megatap.themovies.ui.viewmodel.ItemViewModel;

import java.util.ArrayList;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/26/15.
 */
public abstract class BaseRecycleViewAdapter<ITEM_T, VIEW_MODEL_T extends ItemViewModel<ITEM_T>>
        extends RecyclerView.Adapter<BaseRecycleViewAdapter.ItemViewHolder<ITEM_T, VIEW_MODEL_T>> {

    protected final ArrayList<ITEM_T> items;

    public BaseRecycleViewAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public final void onBindViewHolder(ItemViewHolder<ITEM_T, VIEW_MODEL_T> holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder<T, VT extends ItemViewModel<T>>
            extends RecyclerView.ViewHolder {

        protected final VT viewModel;
        private final ViewDataBinding binding;

        public ItemViewHolder(View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void setItem(T item) {
            viewModel.setItem(item);
            binding.executePendingBindings();
        }
    }
}