package com.example.allpet_ver1;

import android.os.Parcelable;

public class board {

    private String url;
    private String title;
    private int situation;

    public board(String url, String title, int situation) {

        this.url = url;
        this.title = title;
        this.situation= situation;

    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public int getSituation() {
        return situation;
    }

}