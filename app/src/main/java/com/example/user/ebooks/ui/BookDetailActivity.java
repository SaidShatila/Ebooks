package com.example.user.ebooks.ui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.ebooks.R;
import com.example.user.ebooks.base.Book;
import com.example.user.ebooks.db.RealmHelper;

import io.realm.Realm;

public class BookDetailActivity extends AppCompatActivity {

    private ImageView BookThumbnailImg, BookCoverImg;
    private TextView book_title, book_description;
    private FloatingActionButton play_fab;
    private MaterialButton buttonFavorite;
    private FloatingActionButton playFloatingButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        // ini views
        iniViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                onBackPressed();
                return true;
            }
        }
            return super.onOptionsItemSelected(item);
    }

    void iniViews() {
        play_fab = findViewById(R.id.playFloatingActionButton);
        String bookTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        Book parcelableBook = getIntent().getParcelableExtra("book");
        Book realmBook=  RealmHelper.getBookById(Realm.getDefaultInstance(),parcelableBook.getId());
        final Book book=realmBook!=null? realmBook : parcelableBook;
        playFloatingButton = findViewById(R.id.playFloatingActionButton);
        playFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setInProgress(!book.isInProgress());
                RealmHelper.insertBook(Realm.getDefaultInstance(),book);
                refreshInProgressButton(book);
            }
        });
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
        BookCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
        //back button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonFavorite = findViewById(R.id.buttonAddToFavorite);
        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book.setInFavorite(!book.isInFavorite());
                RealmHelper.insertBook(Realm.getDefaultInstance(),book);
                refreshFavoriteIcon(book);
            }
        });
        refreshFavoriteIcon(book);
        refreshInProgressButton(book);
    }

    private void refreshInProgressButton(Book book) {
        if(book.isInProgress()){
            playFloatingButton.setImageResource(R.drawable.ic_bookmark_black_24dp);
        }
        else {
            playFloatingButton.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        }
    }

    private void refreshFavoriteIcon(Book book) {
        if (book.isInFavorite()) {
            buttonFavorite.setIconResource(R.drawable.ic_favorite);
            buttonFavorite.setIconTint(ColorStateList.valueOf(Color.RED));
        } else {
            buttonFavorite.setIconResource(R.drawable.ic_unfavorite);
            buttonFavorite.setIconTint(ColorStateList.valueOf(Color.WHITE));
        }
    }
}