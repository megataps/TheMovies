package com.megatap.themovies.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.megatap.themovies.R;
import com.megatap.themovies.di.component.BaseComponent;
import com.megatap.themovies.model.MovieSortType;
import com.megatap.themovies.ui.base.BaseActivity;
import com.megatap.themovies.ui.base.BaseViewModel;
import com.megatap.themovies.ui.fragment.MovieListFragment;
import com.megatap.themovies.ui.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.adapter_view)
    ViewPager mViewPager;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initUi();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupComponent(BaseComponent baseComponent) {
        // Do nothing
    }

    @Override
    protected void initUi() {
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setNavigationIcon(R.drawable.ic_movie_white_36dp);

        MoviesPagerAdapter adapter = new MoviesPagerAdapter(this, getSupportFragmentManager());
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.TOP_RATED), MovieSortType.TOP_RATED);
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.POPULAR), MovieSortType.POPULAR);
        adapter.addFragment(MovieListFragment.newInstance(MovieSortType.UPCOMING), MovieSortType.UPCOMING);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Nullable
    @Override
    protected BaseViewModel createViewModel(@Nullable BaseViewModel.State savedViewModelState) {
        mMainViewModel = new MainViewModel(savedViewModelState);
        return mMainViewModel;
    }

    private static class MoviesPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<MovieSortType> mFragmentTitles = new ArrayList<>();

        private Context mContext;

        public MoviesPagerAdapter(Context context, FragmentManager fm) {
            super(fm);

            mContext = context;
        }

        public void addFragment(Fragment fragment, MovieSortType title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            MovieSortType type = mFragmentTitles.get(position);
            switch (type) {
                case POPULAR:
                    return  mContext.getString(R.string.popular);
                case UPCOMING:
                    return mContext.getString(R.string.upcoming);
                case TOP_RATED:
                    return mContext.getString(R.string.top_rated);

                default:
                    return null;
            }
        }
    }
}
