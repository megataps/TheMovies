package com.megatap.themovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.megatap.themovies.model.Movie;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.model.MoviesListWrapper;
import com.megatap.themovies.service.Callback;
import com.megatap.themovies.service.MovieService;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    List<Movie> mMovieList;

    @Inject
    MovieService mMovieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TMApplication.getBaseComponent().inject(this);

        loadData();
    }

    private void loadData() {

        mMovieService.getMovies(1, MovieSortType.POPULAR, new Callback<MoviesListWrapper>() {
            @Override
            public void onSuccess(MoviesListWrapper response) {
                mMovieList = response.getResults();
                Log.e("DDDMMM", "mMovieList.size():" + mMovieList.size());
                Toast.makeText(MainActivity.this, String.valueOf(mMovieList.size()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
