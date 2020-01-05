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

        ahBottomNavigation.addItem(new AHBottomNavigationItem("Home",R.drawable.home));
        ahBottomNavigation.addItem(new AHBottomNavigationItem("MyBooks",R.drawable.children));



    }
}




