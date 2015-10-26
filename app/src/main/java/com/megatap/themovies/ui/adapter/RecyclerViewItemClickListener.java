package com.megatap.themovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.megatap.themovies.util.ViewUtil;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/24/15.
 */
public class RecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener{

    private OnItemGestureListener mListener;
    private View mViewUnderTouch;
    private RecyclerView mRecyclerView;
    private GestureDetector mGestureDetector;

    public interface OnItemGestureListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }



    public RecyclerViewItemClickListener(Context context, OnItemGestureListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
            @Override public void onLongPress(MotionEvent e) {
                int position = mRecyclerView.getChildAdapterPosition(mViewUnderTouch);
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemLongClick(mViewUnderTouch, position);
                }
            }
        });
        mGestureDetector.setIsLongpressEnabled(true);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());

        /* Return false early if we know that the click has been made on a subview
         * that has an onClick listener set on it. This will allow propagating the
         * click event to child views.
         */
        if(ViewUtil.isTouchInsideViewWithClickListener(childView, e)) {
            return false;
        }

        /* Else we consider the click event for the entire recycler view item */
        mViewUnderTouch = childView;
        mRecyclerView = view;
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}