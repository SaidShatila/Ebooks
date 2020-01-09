package com.example.user.ebooks.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.ebooks.R;
import com.example.user.ebooks.base.Book;
import com.example.user.ebooks.db.DataSource;
import com.example.user.ebooks.functionalities.BookAdapter;
import com.example.user.ebooks.functionalities.BookItemClickListener;
import com.example.user.ebooks.functionalities.SliderPagerAdapter;
import com.example.user.ebooks.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment implements BookItemClickListener {

    private ViewPager sliderPagerViewPager;
    private TabLayout indicatorTabLayout;
    private List<Book> firstSlidesList;
    private RecyclerView BooksRV,BooksRvLastWeek;
    private BookAdapter lastWeekBookAdapter;
    private BookAdapter movieAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        String title;
        if (SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium()) {
            title = "Switch To Freemium";
        } else {
            title = "Switch To Premium";
        }
        menu.findItem(R.id.itemSwitchToPremium).setTitle(title);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home,menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSwitchToPremium: {
                SharedPreferenceHelper.getCurrentInstance(getContext()).setIsPremium(!SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
                getActivity().recreate();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);

//        iniViews();
//        iniSlider();
//        iniPopularBooks();
//        iniLastWeekBooks();

    }

    public void setUpViews(View root){


        BooksRV = root.findViewById(R.id.popularBooksRecylerView);
        BooksRvLastWeek = root.findViewById(R.id.Rv_books_last_week);

        sliderPagerViewPager = root.findViewById(R.id.slider_pagerViewPager);
        indicatorTabLayout = root.findViewById(R.id.indicatorTabLayout);



        // prepare a list of slides ..
        firstSlidesList = new ArrayList<>();
        Book book1 = new Book("Dracula",R.drawable.dracula,R.drawable.dracula,13);
        firstSlidesList.add(book1);
        Book book2 = new Book("The Tree",R.drawable.tree,R.drawable.tree,14);
        firstSlidesList.add(book2);
        firstSlidesList.add(new Book("Peter Pan",R.drawable.peterpan,R.drawable.peterpan,15));
        Book book5 = new Book("Poetry Of Art",R.drawable.poetryofart,R.drawable.poetryofart,17);
        firstSlidesList.add(book5);
        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(), firstSlidesList, SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
        sliderPagerViewPager.setAdapter(adapter);

        sliderPagerViewPager.setAdapter(adapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 4000, 6000);
        indicatorTabLayout.setupWithViewPager(sliderPagerViewPager, true);

        // Recyclerview Setup
        // ini data


        lastWeekBookAdapter = new BookAdapter(getContext(), DataSource.getLastWeekBook(),this,SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
        BooksRvLastWeek.setAdapter(lastWeekBookAdapter);
        BooksRvLastWeek.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        movieAdapter = new BookAdapter(getContext(), DataSource.getPopularBooks(),this,SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
        BooksRV.setAdapter(movieAdapter);
        BooksRV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

    }

    @Override
    public void onBookClick(final Book book, final ImageView movieImageView) {
        if (book.isPremium()) {

            if (SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium()) {
//book premium and user is premium ==> proceed
                proceedToBook(book, movieImageView);
            } else {
//book premium and user is NOT premium ==>  Show Dialog
                AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("Warning")
                        .setMessage("Do you want to switch to Premium")
                        .setPositiveButton("Switch", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferenceHelper.getCurrentInstance(getContext()).setIsPremium(true);
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

        Toast.makeText(getContext(), "item clicked : " + book.getTitle(), Toast.LENGTH_LONG).show();
        // it works great


    }
    class SliderTimer extends TimerTask {


        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
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


    private void refreshData() {
        lastWeekBookAdapter.setUserPremium(SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
        lastWeekBookAdapter.notifyDataSetChanged();
        movieAdapter.setUserPremium(SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
        movieAdapter.notifyDataSetChanged();


    }

    private void proceedToBook(Book book, ImageView movieImageView) {
        Intent intent = new Intent(getContext(), BookDetailActivity.class);

        intent.putExtra("title", book.getTitle());
        intent.putExtra("imgURL", book.getThumbnail());
        intent.putExtra("imgCover", book.getCoverPhoto());
        intent.putExtra("book",book);
        // lets crezte the animation
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                movieImageView, "sharedName");

        ActivityCompat.startActivity(getContext(), intent, options.toBundle());
    }


}
