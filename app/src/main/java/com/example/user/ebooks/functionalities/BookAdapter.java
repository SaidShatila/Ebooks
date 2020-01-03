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

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    Context context ;
    List<Book> mData;
    BookItemClickListener bookItemClickListener;


    public BookAdapter(Context context, List<Book> mData, BookItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        bookItemClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_book,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        myViewHolder.BookTitle.setText(mData.get(i).getTitle());
        myViewHolder.ImgBook.setImageResource(mData.get(i).getThumbnail());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView BookTitle;
        private ImageView ImgBook;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            BookTitle= itemView.findViewById(R.id.itemBookTitleTextView);
            ImgBook = itemView.findViewById(R.id.itemBookImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    bookItemClickListener.onBookClick(mData.get(getAdapterPosition()),ImgBook);


                }
            });

        }
    }
}