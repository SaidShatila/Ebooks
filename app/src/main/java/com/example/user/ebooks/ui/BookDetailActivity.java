package com.example.user.ebooks.ui;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.user.ebooks.R;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView BookThumbnailImg,BookCoverImg;
    private TextView book_title,book_description;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        // ini views
        iniViews();




    }

    void iniViews() {
        play_fab = findViewById(R.id.playFloatingActionButton);
        String bookTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        BookThumbnailImg = findViewById(R.id.detailBookImageView);
        Glide.with(this).load(imageResourceId).into(BookThumbnailImg);
        BookThumbnailImg.setImageResource(imageResourceId);
        BookCoverImg = findViewById(R.id.detailBookCoverImageView);
        Glide.with(this).load(imagecover).into(BookCoverImg);
        book_title = findViewById(R.id.detailBookTitleTextView);
        book_title.setText(bookTitle);
        getSupportActionBar().setTitle(bookTitle);
        book_description = findViewById(R.id.detailBookDescriptionTextView);
        // setup animation
        BookCoverImg.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scale_animation));





    }


}