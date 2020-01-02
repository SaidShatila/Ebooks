package com.example.user.ebooks;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements BookItemClickListener {

    private List<Slide> firstSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView MoviesRV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sliderpager = findViewById(R.id.slider_pager) ;
        indicator = findViewById(R.id.indicator);
        MoviesRV = findViewById(R.id.Rv_books);

        // prepare a list of slides ..
        firstSlides = new ArrayList<>() ;
        firstSlides.add(new Slide(R.drawable.agenls,"Angels & Demons"));
        firstSlides.add(new Slide(R.drawable.children,"Children Of Darkness"));
        firstSlides.add(new Slide(R.drawable.hell,"Hell Divers VI Allegiance"));
        firstSlides.add(new Slide(R.drawable.night,"Sharing NightMares"));
        firstSlides.add(new Slide(R.drawable.me,"Someone Like ME"));
        firstSlides.add(new Slide(R.drawable.science,"Putting The Science In Fiction"));
        SliderPagerAdapter adapter = new SliderPagerAdapter(this,firstSlides);
        sliderpager.setAdapter(adapter);

        sliderpager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeActivity.SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);

        // Recyclerview Setup
        // ini data

        List<Book> lstBooks = new ArrayList<>();
        lstBooks.add(new Book("Someone Like Me",R.drawable.me,R.drawable.me));
        lstBooks.add(new Book("Children of Darkness",R.drawable.children,R.drawable.children));
        lstBooks.add(new Book("Sharing NightMares",R.drawable.night));
        lstBooks.add(new Book("Hell Divers VI Allegiance",R.drawable.hell));
        lstBooks.add(new Book("The Martian",R.drawable.hell));
        lstBooks.add(new Book("The Martian",R.drawable.hell));


        BookAdapter movieAdapter = new BookAdapter(this,lstBooks,this);
        MoviesRV.setAdapter(movieAdapter);
        MoviesRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));





    }

    @Override
    public void onBookClick(Book book, ImageView movieImageView) {


        Intent intent = new Intent(this,BookDetailActivity.class);

        intent.putExtra("title",book.getTitle());
        intent.putExtra("imgURL",book.getThumbnail());
        intent.putExtra("imgCover",book.getCoverPhoto());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(HomeActivity.this,
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

        Toast.makeText(this,"item clicked : " + book.getTitle(),Toast.LENGTH_LONG).show();
        // it works great


    }

    class SliderTimer extends TimerTask {


        @Override
        public void run() {

            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<firstSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });


        }
    }





}




