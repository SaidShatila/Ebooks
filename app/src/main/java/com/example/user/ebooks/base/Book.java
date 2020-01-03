package com.example.user.ebooks.base;

public class Book {
    private String title;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private String BookLink;
    private int coverPhoto;
    private boolean isPremium;


    public Book(String title, int thumbnail, int coverPhoto) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = coverPhoto;
    }

    public Book(String title, int thumbnail,boolean isPremium) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.isPremium=isPremium;
    }

    public Book(String title, String description, int thumbnail, String studio, String rating, String BookLink, boolean isPremium) {
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.studio = studio;
        this.rating = rating;
        this.BookLink = BookLink;
        this.isPremium = isPremium;
    }


    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getStudio() {
        return studio;
    }

    public String getRating() {
        return rating;
    }

    public String getBookLink() {
        return BookLink;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setBookLink(String BookLink) {
        this.BookLink = BookLink;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

}