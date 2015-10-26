package com.megatap.themovies.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.megatap.themovies.R;
import com.megatap.themovies.di.component.BaseComponent;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.model.MoviesListWrapper;
import com.megatap.themovies.service.Callback;
import com.megatap.themovies.service.MovieService;
import com.megatap.themovies.ui.adapter.MovieListAdapter;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by Jackie Nguyen <nguyenngoc100@gmail.com> on 10/26/15.
 */
public class MovieListFragment extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener{

    private MovieListAdapter mMoviesAdapter;
    private MovieSortType mMovieSortType;
//    private int page = 1;
//    List<Movie> mMovieList;

    @Inject
    MovieService mMovieService;

    @Bind(R.id.refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.adapter_view)
    RecyclerView mRecyclerView;

    //Bundle keys
    private static final String KEY_MOVIE_SORT_TYPE = "key_movie_sort_type";

    @NonNull
    public static BaseFragment newInstance(@NonNull MovieSortType movieSortType) {
        Bundle b = new Bundle();
        b.putSerializable(KEY_MOVIE_SORT_TYPE, movieSortType);
        MovieListFragment f = new MovieListFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(KEY_MOVIE_SORT_TYPE)) {
                Serializable serializable = args.getSerializable(KEY_MOVIE_SORT_TYPE);
                if (serializable instanceof MovieSortType) {
                    mMovieSortType = (MovieSortType) serializable;
                }
            }
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareUi(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        prepareData();
    }


    @Override
    protected void setupComponent(BaseComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    protected void prepareUi(@NonNull View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.oker_dark, R.color.blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mMoviesAdapter = new MovieListAdapter();
//        mMoviesAdapter.setInfiniteAdapterListener(new OnAdapterLastItemReachListener() {
//            @Override
//            public void onLastItemReached() {
//                page += 1;
//                requestMoviesFromBackend(page);
//            }
//        });
//        mMoviesAdapter.setMovieItemClickListener(this);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mRecyclerView.setAdapter(mMoviesAdapter);
    }

    @Override
    protected void prepareData() {
        mMovieService.getMovies(1, mMovieSortType, new Callback<MoviesListWrapper>() {
            @Override
            public void onSuccess(MoviesListWrapper response) {
                mMoviesAdapter.setData(response.getResults());

                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        prepareData();
    }
}
