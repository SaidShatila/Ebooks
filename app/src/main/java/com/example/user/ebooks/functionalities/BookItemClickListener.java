package com.example.user.ebooks.functionalities;

import android.widget.ImageView;

import com.example.user.ebooks.base.Book;

public interface BookItemClickListener {

    void onBookClick(Book book, ImageView bookImageView);

}