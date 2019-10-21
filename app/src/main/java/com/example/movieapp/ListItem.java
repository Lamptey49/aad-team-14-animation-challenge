package com.example.movieapp;

public class ListItem extends MovieItem {
    private String mImageUrl;
    private String mMovieTitle;
    private int mVotes;

    public ListItem(String imageUrl, String mmovietitle, int mvotes) {
        this.mImageUrl = imageUrl;
        this.mMovieTitle = mmovietitle;
        this.mVotes = mvotes;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getmMovieTitle() {
        return mMovieTitle;
    }

    public int getmVotes() {
        return mVotes;
    }
}
