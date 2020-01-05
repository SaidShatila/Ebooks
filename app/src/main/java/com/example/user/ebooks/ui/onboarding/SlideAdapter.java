package com.example.user.ebooks.ui.onboarding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.ebooks.R;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public SlideAdapter(Context context){
        this.context    = context;
    }
    public int[] slide_images={

            R.drawable.home,
            R.drawable.codeicon,
            R.drawable.sleepicon

    };
    public String[] slide_headings={
            "Welcome!",
            "Your new Fav!",
            "Enjoy Reading!"
    };
    public String[] slide_design={
            "Let's get started! Planning to change your lifestyle to a new one, get more educated,fill your time..",
            "We'll keep you updated with the newest most modern books up to your genre choice!",
            "Well help organize your library and sell books at the lowest prices! What are you waiting for jump in!"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout)object;
    }
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slidelayout,container,false);
        ImageView slideImageView = (ImageView)view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView)view.findViewById(R.id.slide_heading);
        TextView slideDescription =(TextView)view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_design[position]);
        container.addView(view);
        return view;

    }
    @Override
    public void destroyItem(ViewGroup container, int position,Object object){
        container.removeView((RelativeLayout)object);
    }
}
