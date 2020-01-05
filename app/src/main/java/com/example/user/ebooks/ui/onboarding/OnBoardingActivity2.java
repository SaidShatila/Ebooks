package com.example.user.ebooks.ui.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.ebooks.R;

public class OnBoardingActivity2 extends AppCompatActivity {
    private ViewPager slideviewerPage;
    private LinearLayout dots;
    private SlideAdapter slideAdapter;
    private TextView[] adots;
    private Button BackBtn;
    private Button NextBtn;
    private Button button;
    private int currPage;
    private TextView finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding2);

        slideviewerPage = (ViewPager)findViewById(R.id.slideviewerPage);
        dots = (LinearLayout)findViewById(R.id.dots);
        NextBtn = (Button)findViewById(R.id.nxtbtn);
        BackBtn=(Button)findViewById(R.id.bckbtn);
        slideAdapter = new SlideAdapter(this);
        slideviewerPage.setAdapter(slideAdapter);
        addDotsIndicator(0);
        slideviewerPage.addOnPageChangeListener(viewListener);
        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currPage==2){
                    NextBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openRegister();
                        }
                    });

                }
else {
                    slideviewerPage.setCurrentItem(currPage + 1);
                }
            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideviewerPage.setCurrentItem(currPage-1);
            }
        });

    }
    public void addDotsIndicator(int position){
        adots = new TextView[3];
        dots.removeAllViews();
        for(int i=0;i<adots.length;i++){
            adots[i] = new TextView(this);
            adots[i].setText(Html.fromHtml("&#8226;"));
            adots[i].setTextSize(35);
            adots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            dots.addView(adots[i]);
        }
        if(adots.length > 0){
            adots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currPage=position;
            if(position == 0){
                NextBtn.setEnabled(true);
                BackBtn.setEnabled(false);
                BackBtn.setVisibility(View.INVISIBLE);
                NextBtn.setText("Next");
                BackBtn.setText("");
            }
            else if(position ==adots.length-1){
                NextBtn.setEnabled(true);
                BackBtn.setEnabled(true);
                BackBtn.setVisibility(View.VISIBLE);
                NextBtn.setText("Finish");
                BackBtn.setText("Back");


            }
            else {
                NextBtn.setEnabled(true);
                BackBtn.setEnabled(true);
                BackBtn.setVisibility(View.VISIBLE);
                NextBtn.setText("Next");
                BackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    public void openRegister(){
        Intent intent = new Intent(OnBoardingActivity2.this,OnBoardingActivity3.class);
        startActivity(intent);


    }
}
