package com.megatap.themovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesListWrapper implements Parcelable {

    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("total_results")
    private int totalItems;

    private List<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "MoviesListWrapper{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", totalItems=" + totalItems +
                ", results=" + results +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalPages);
        dest.writeInt(this.totalItems);
        dest.writeTypedList(results);
    }

    public MoviesListWrapper() {
    }

    protected MoviesListWrapper(Parcel in) {
        this.page = in.readInt();
        this.totalPages = in.readInt();
        this.totalItems = in.readInt();
        this.results = in.createTypedArrayList(Movie.CREATOR);
    }

    public static final Parcelable.Creator<MoviesListWrapper> CREATOR = new Parcelable.Creator<MoviesListWrapper>() {
        public MoviesListWrapper createFromParcel(Parcel source) {
            return new MoviesListWrapper(source);
        }

        public MoviesListWrapper[] newArray(int size) {
            return new MoviesListWrapper[size];
        }
    };
}
