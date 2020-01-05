package com.example.user.ebooks.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.ebooks.base.Book;
import com.example.user.ebooks.functionalities.BookAdapter;
import com.example.user.ebooks.functionalities.BookItemClickListener;
import com.example.user.ebooks.R;
import com.example.user.ebooks.functionalities.SliderPagerAdapter;
import com.example.user.ebooks.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements BookItemClickListener {

    private List<Book> firstSlidesList;
    private ViewPager sliderPagerViewPager;
    private TabLayout indicatorTabLayout;
    private RecyclerView moviesRecylerView;
    private BookAdapter movieAdapter;


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String title;
        if (SharedPreferenceHelper.getCurrentInstance(this).getIsPremium()) {
            title = "Switch To Freemium";
        } else {
            title = "Switch To Premium";
        }
        menu.findItem(R.id.itemSwitchToPremium).setTitle(title);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSwitchToPremium: {
                SharedPreferenceHelper.getCurrentInstance(this).setIsPremium(!SharedPreferenceHelper.getCurrentInstance(this).getIsPremium());
                recreate();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        sliderPagerViewPager = findViewById(R.id.slider_pagerViewPager);
        indicatorTabLayout = findViewById(R.id.indicatorTabLayout);
        moviesRecylerView = findViewById(R.id.booksRecylerView);

        // prepare a list of slides ..
        firstSlidesList = new ArrayList<>();
        Book book1 = new Book("Angels & Demons", R.drawable.agenls, R.drawable.agenls);
        book1.setPremium(true);
        firstSlidesList.add(book1);
        Book book2 = new Book("Children Of Darkness", R.drawable.children, R.drawable.children);
        book2.setPremium(true);
        firstSlidesList.add(book2);
        firstSlidesList.add(new Book("Hell Divers VI Allegiance", R.drawable.hell, R.drawable.hell));
        firstSlidesList.add(new Book("Sharing NightMares", R.drawable.night, R.drawable.night));
        Book book5 = new Book("Someone Like ME", R.drawable.me, R.drawable.me);
        book5.setPremium(true);
        firstSlidesList.add(book5);
        firstSlidesList.add(new Book("Putting The Science In Fiction", R.drawable.science, R.drawable.science));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, firstSlidesList, SharedPreferenceHelper.getCurrentInstance(this).getIsPremium());
        sliderPagerViewPager.setAdapter(adapter);

        sliderPagerViewPager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeActivity.SliderTimer(), 4000, 6000);
        indicatorTabLayout.setupWithViewPager(sliderPagerViewPager, true);

        // Recyclerview Setup
        // ini data

        List<Book> lstBooks = new ArrayList<>();
        lstBooks.add(new Book("Someone Like Me", R.drawable.me, R.drawable.me));
        lstBooks.add(new Book("Children of Darkness", R.drawable.children, R.drawable.children));
        lstBooks.add(new Book("Sharing NightMares", R.drawable.night, false));
        lstBooks.add(new Book("Hell Divers VI Allegiance", R.drawable.hell, true));
        lstBooks.add(new Book("The Martian", R.drawable.hell, true));
        lstBooks.add(new Book("The Martian", R.drawable.hell, true));


        movieAdapter = new BookAdapter(this, lstBooks, this, SharedPreferenceHelper.getCurrentInstance(this).getIsPremium());
        moviesRecylerView.setAdapter(movieAdapter);
        moviesRecylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


    }

    @Override
    public void onBookClick(final Book book, final ImageView movieImageView) {
        if (book.isPremium()) {

            if (SharedPreferenceHelper.getCurrentInstance(this).getIsPremium()) {
//book premium and user is premium ==> proceed
                proceedToBook(book, movieImageView);
            } else {
//book premium and user is NOT premium ==>  Show Dialog
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Warning")
                        .setMessage("Do you want to switch to Premium")
                        .setPositiveButton("Switch", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceHelper.getCurrentInstance(HomeActivity.this).setIsPremium(true);
                                proceedToBook(book, movieImageView);
                                refreshData();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                alertDialog.show();

            }

        } else {
            //book is not premium ==> proceed
            proceedToBook(book, movieImageView);
        }

        // i l make a simple test to see if the click works

        Toast.makeText(this, "item clicked : " + book.getTitle(), Toast.LENGTH_LONG).show();
        // it works great


    }

    private void refreshData() {
        movieAdapter.setUserPremium(SharedPreferenceHelper.getCurrentInstance(this).getIsPremium());
        movieAdapter.notifyDataSetChanged();
    }

    private void proceedToBook(Book book, ImageView movieImageView) {
        Intent intent = new Intent(this, BookDetailActivity.class);

        intent.putExtra("title", book.getTitle());
        intent.putExtra("imgURL", book.getThumbnail());
        intent.putExtra("imgCover", book.getCoverPhoto());
        // lets crezte the animation
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this,
                movieImageView, "sharedName");

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    class SliderTimer extends TimerTask {


        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPagerViewPager.getCurrentItem() < firstSlidesList.size() - 1) {
                        sliderPagerViewPager.setCurrentItem(sliderPagerViewPager.getCurrentItem() + 1);
                    } else
                        sliderPagerViewPager.setCurrentItem(0);
                }
            });

        }
    }


}




