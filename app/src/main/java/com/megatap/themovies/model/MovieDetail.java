package com.megatap.themovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


public class MovieDetail implements Parcelable {

    private List<Genre> genres;

    private long id;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("release_date")
    private Date releaseDate;

    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    private String homepage;

    @SerializedName("vote_average")
    private double voteAverage;

    private double popularity;

    public List<Genre> getGenres() {
        return genres;
    }

    public long getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    @Override
    public String toString() {
        return "MovieDetail{" +
                "genres=" + genres +
                ", id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", releaseDate=" + releaseDate +
                ", overview='" + overview + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", homepage='" + homepage + '\'' +
                ", voteAverage=" + voteAverage +
                ", popularity=" + popularity +
                '}';
    }

    public String getHomepage() {
        return homepage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(genres);
        dest.writeLong(this.id);
        dest.writeString(this.originalTitle);
        dest.writeLong(releaseDate != null ? releaseDate.getTime() : -1);
        dest.writeString(this.overview);
        dest.writeString(this.posterPath);
        dest.writeString(this.homepage);
        dest.writeDouble(this.voteAverage);
        dest.writeDouble(this.popularity);
    }

    public MovieDetail() {
    }

    protected MovieDetail(Parcel in) {
        this.genres = in.createTypedArrayList(Genre.CREATOR);
        this.id = in.readLong();
        this.originalTitle = in.readString();
        long tmpReleaseDate = in.readLong();
        this.releaseDate = tmpReleaseDate == -1 ? null : new Date(tmpReleaseDate);
        this.overview = in.readString();
        this.posterPath = in.readString();
        this.homepage = in.readString();
        this.voteAverage = in.readDouble();
        this.popularity = in.readDouble();
    }

    public static final Parcelable.Creator<MovieDetail> CREATOR = new Parcelable.Creator<MovieDetail>() {
        public MovieDetail createFromParcel(Parcel source) {
            return new MovieDetail(source);
        }

        public MovieDetail[] newArray(int size) {
            return new MovieDetail[size];
        }
    };
}