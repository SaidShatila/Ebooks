package com.example.user.ebooks.functionalities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.ebooks.base.Book;
import com.example.user.ebooks.R;
import com.example.user.ebooks.utils.SharedPreferenceHelper;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    Context context ;
    List<Book> mData;
    BookItemClickListener bookItemClickListener;
 private    boolean isUserPremium;

    public void setUserPremium(boolean userPremium) {
        isUserPremium = userPremium;
    }

    public BookAdapter(Context context, List<Book> mData, BookItemClickListener listener, boolean isUserPremium) {
        this.context = context;
        this.mData = mData;
        bookItemClickListener = listener;
        this.isUserPremium=isUserPremium;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {



        View view = LayoutInflater.from(context).inflate(R.layout.item_book,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Book book= mData.get(i);
        if(book.isPremium()){
            myViewHolder.lockIcon.setVisibility(View.VISIBLE);

            if(isUserPremium){
//book premium and user is premium ==> unlocked icon

            myViewHolder.lockIcon.setImageResource(R.drawable.ic_unlock);
            }
            else {
//book premium and user is NOT premium ==> locked icon
                myViewHolder.lockIcon.setImageResource(R.drawable.ic_locked);
            }
        }
        else{
            //book is not premium ==> hide lock icon
            myViewHolder.lockIcon.setVisibility(View.GONE);
        }
        myViewHolder.BookTitle.setText(book.getTitle());
        myViewHolder.ImgBook.setImageResource(book.getThumbnail());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView BookTitle;
        private ImageView ImgBook;
        private ImageView lockIcon;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            BookTitle= itemView.findViewById(R.id.itemBookTitleTextView);
            ImgBook = itemView.findViewById(R.id.itemBookImageView);
            lockIcon= itemView.findViewById(R.id.lockIndicatorImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bookItemClickListener.onBookClick(mData.get(getAdapterPosition()),ImgBook);


                }
            });

        }
    }
}