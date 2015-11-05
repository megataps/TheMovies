package com.megatap.themovies.ui.activity;

import android.os.Bundle;

import com.megatap.themovies.R;
import com.megatap.themovies.di.component.BaseComponent;
import com.megatap.themovies.ui.fragment.MovieDetailsFragment;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 11/5/15.
 */
public class MovieDetailsActivity extends BaseActivity {

    private static final String ARG_MOVIE_ID = "ARG_MOVIE_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUi();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fragment_container;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        // Do nothing
    }

    @Override
    protected void initUi() {
        long movieId = 0;
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(ARG_MOVIE_ID)) {
            movieId = extras.getLong(ARG_MOVIE_ID);
        }

        replaceFragment(R.id.fragment_container, MovieDetailsFragment.newInstance(movieId), false);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
