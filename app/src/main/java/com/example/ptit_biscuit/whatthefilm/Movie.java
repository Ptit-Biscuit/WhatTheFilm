package com.example.ptit_biscuit.whatthefilm;

import android.widget.ImageView;

/**
 * Created by Ptit-Biscuit on 26/03/2017.
 */

public class Movie {
    // param title
    private String name;
    // param overview
    private String desc;
    // param release_date
    private String date;
    // param poster_path
    private ImageView poster;

    public Movie(String name, String desc, String date) {
        this.name = name;
        this.desc = desc;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ImageView getPoster() {
        return poster;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }
}
