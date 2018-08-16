package com.example.android.recycler_json_orientation_fragments;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

public class JSO implements Serializable {

    private String rating;
    private String mTitle;
    private String poster;
    private String lang;
    private String back;
    private String rDate;
    private String over;
    private String adult;
    private ArrayList<String> genre;
    private Bitmap post;
    private Bitmap backdrop;

    public JSO()
    {
        this.rating = null;
        this.mTitle = null;
        this.poster = null;
        this.lang = null;
        this.back = null;
        this.rDate = null;
        this.over = null;
        this.adult = null;
        this.genre = null;
        this.backdrop = null;
        this.post = null;
    }

    public String getRating() {
        return rating;
    }

    public Bitmap getBackdrop() {
        return backdrop;
    }

    public Bitmap getPost() {
        return post;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getPoster() {
        return poster;
    }

    public String getLang() {
        return lang;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public String getAdult() {
        return adult;
    }

    public String getBack() {
        return back;
    }

    public String getrDate() {
        return rDate;
    }

    public String getOver() {
        return over;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public void setBackdrop(Bitmap backdrop) {
        this.backdrop = backdrop;
    }

    public void setPost(Bitmap post) {
        this.post = post;
    }
}
