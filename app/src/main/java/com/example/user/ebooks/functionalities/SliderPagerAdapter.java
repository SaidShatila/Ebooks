package com.example.user.ebooks.functionalities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.user.ebooks.R;
import com.example.user.ebooks.base.Book;


import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Book> mList;
    private boolean isUserPremium;

    public SliderPagerAdapter(Context mContext, List<Book> mList, boolean isUserPremium) {
        this.mContext = mContext;
        this.mList = mList;
        this.isUserPremium = isUserPremium;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater inflater = LayoutInflater.from(mContext);
        View slideLayout = inflater.inflate(R.layout.slider_item, null);

        ImageView slideImg = slideLayout.findViewById(R.id.slideImageImageView);
        TextView slideText = slideLayout.findViewById(R.id.slideTitleTextView);
        slideImg.setImageResource(mList.get(position).getCoverPhoto());
        slideText.setText(mList.get(position).getTitle());
        ImageView lockIcon = slideLayout.findViewById(R.id.lockIndicatorImageView);
        Book book = mList.get(position);

        if (book.isPremium()) {
            lockIcon.setVisibility(View.VISIBLE);

            if (isUserPremium) {
//book premium and user is premium ==> unlocked icon

                lockIcon.setImageResource(R.drawable.ic_unlock);
            } else {
//book premium and user is NOT premium ==> locked icon
                lockIcon.setImageResource(R.drawable.ic_locked);
            }
        } else {
            //book is not premium ==> hide lock icon
            lockIcon.setVisibility(View.GONE);
        }

        container.addView(slideLayout);
        return slideLayout;


    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}