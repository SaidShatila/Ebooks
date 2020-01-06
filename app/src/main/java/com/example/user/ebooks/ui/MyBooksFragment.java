package com.example.user.ebooks.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.ebooks.R;
import com.example.user.ebooks.base.Book;
import com.example.user.ebooks.db.RealmHelper;
import com.example.user.ebooks.functionalities.BookAdapter;
import com.example.user.ebooks.functionalities.SliderPagerAdapter;
import com.example.user.ebooks.utils.SharedPreferenceHelper;

import java.util.ArrayList;

import io.realm.Realm;

public class MyBooksFragment extends Fragment {

    private View textviewFavorites;
    private View textviewInProgress;
    private RecyclerView recyclerViewInProgress;
    private RecyclerView recyclerViewFavorites;
    BookAdapter bookAdapterInProgress;
    BookAdapter bookAdapterFavorites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_books,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         setUpViews(view);
    }
    public void setUpViews(View root){
        textviewFavorites = root.findViewById(R.id.textViewFavorites);
        textviewInProgress = root.findViewById(R.id.textViewInProgress);
        recyclerViewInProgress = root.findViewById(R.id.inProgressRecylerView);
        recyclerViewInProgress.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewFavorites   = root.findViewById(R.id.favoriteBookRecylerView);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        bookAdapterFavorites = new BookAdapter(getContext(), new ArrayList<Book>(),null, SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());
        bookAdapterInProgress = new BookAdapter(getContext(), new ArrayList<Book>(), null,SharedPreferenceHelper.getCurrentInstance(getContext()).getIsPremium());

    }

    @Override
    public void onResume() {
        super.onResume();
        bookAdapterFavorites.setmData(RealmHelper.fetchInFavoriteBooks(Realm.getDefaultInstance()));
        bookAdapterInProgress.setmData(RealmHelper.fetchInProgressBooks(Realm.getDefaultInstance()));
    }
}
