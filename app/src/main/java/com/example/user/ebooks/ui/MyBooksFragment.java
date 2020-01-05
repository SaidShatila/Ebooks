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
import com.example.user.ebooks.functionalities.SliderPagerAdapter;

public class MyBooksFragment extends Fragment {

    private View textviewFavorites;
    private View textviewInProgress;
    private RecyclerView recyclerViewInProgress;
    private RecyclerView recyclerViewFavorites;
    SliderPagerAdapter sliderPagerAdapterInProgress;
    SliderPagerAdapter sliderPagerAdapterFavorites;

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

//        sliderPagerAdapterFavorites = new SliderPagerAdapter();
//        sliderPagerAdapterInProgress= new SliderPagerAdapter();

    }
}
