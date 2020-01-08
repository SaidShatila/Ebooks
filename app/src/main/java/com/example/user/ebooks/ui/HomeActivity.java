package com.example.user.ebooks.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.user.ebooks.R;

public class HomeActivity extends AppCompatActivity {


    private AHBottomNavigation ahBottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_home);
        ahBottomNavigation = findViewById(R.id.bottom_navigation);

        ahBottomNavigation.addItem(new AHBottomNavigationItem("Home",R.drawable.ic_home_black_24dp));
        ahBottomNavigation.addItem(new AHBottomNavigationItem("MyBooks",R.drawable.ic_book_black_24dp));

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position){

                    case 0:{
                        if(!wasSelected) {
                            openHomeFragment();
                        }
                    }
                    break;
                    case 1:{
                        if(!wasSelected) {
                            openMyBooksFragment();
                        }
                    }
                    break;

                }

                return true;
            }
        });
            ahBottomNavigation.setCurrentItem(0,false);
            openHomeFragment();
    }

    private void openMyBooksFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutRoot,new MyBooksFragment(),MyBooksFragment.class.getSimpleName())
        .commitNow();
    }

    private void openHomeFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayoutRoot,new HomeFragment(),HomeFragment.class.getSimpleName())
        .commitNow();
    }
}




