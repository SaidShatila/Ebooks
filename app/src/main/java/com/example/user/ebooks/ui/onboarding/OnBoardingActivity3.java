package com.example.user.ebooks.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.ebooks.R;
import com.example.user.ebooks.ui.HomeActivity;

public class OnBoardingActivity3 extends AppCompatActivity {
    Button buttoncontinueAsGuest;
    Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding3);
        setTheme(R.style.OnBoardingTheme);
        buttoncontinueAsGuest = findViewById(R.id.buttonContinueAsGuest);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttoncontinueAsGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });

    }

    public void goToHome(){


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
