package com.example.user.ebooks.base;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Book extends RealmObject implements Parcelable {
    @PrimaryKey
    private int id;
    private String title;
    private String description;
    private int thumbnail;
    private String studio;
    private String rating;
    private String BookLink;
    private int coverPhoto;
    private boolean isPremium;
    private boolean isInProgress=false;
    private boolean isInFavorite=false;


    public Book(){

    }

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


    protected Book(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        thumbnail = in.readInt();
        studio = in.readString();
        rating = in.readString();
        BookLink = in.readString();
        coverPhoto = in.readInt();
        isPremium = in.readByte() != 0;
        isInProgress = in.readByte() != 0;
        isInFavorite = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

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

    public void setInFavorite(boolean inFavorite) {
        isInFavorite = inFavorite;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }

    public boolean isInFavorite() {
        return isInFavorite;
    }

    public boolean isInProgress() {
        return isInProgress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(thumbnail);
        dest.writeString(studio);
        dest.writeString(rating);
        dest.writeString(BookLink);
        dest.writeInt(coverPhoto);
        dest.writeByte((byte) (isPremium ? 1 : 0));
        dest.writeByte((byte) (isInProgress ? 1 : 0));
        dest.writeByte((byte) (isInFavorite ? 1 : 0));
    }
}