package com.example.mynews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsFragment extends Fragment {
    private RecyclerView recyclerview;
    private NewsViewModel mNewsViewModel;
    private String keyword;
    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_list_news, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerView);
        mNewsViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication())).get(NewsViewModel.class);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            keyword = bundle.getString("keyword", "");
        }
        updateList(keyword);

        return view;
    }

    public void updateList(String keyword) {
        try {
            mNewsViewModel.getNewsEverythingFilter(keyword).observe(getViewLifecycleOwner(), reports -> {
                // Update the cached copy of the words in the adapter.
                if (reports == null){
                    return;
                }
                articleArrayList.addAll(reports.getArticles());
                NewsAdapter recyclerAdapter = new NewsAdapter(getActivity(),articleArrayList);
                recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerview.setAdapter(recyclerAdapter);

            });
        }catch (Exception e){
            e.printStackTrace();
            Log.d("TESTTT","GAMASOK");
        }

    }
}
