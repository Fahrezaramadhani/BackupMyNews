package com.example.mynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<NewsArticle> articleArrayList = new ArrayList<>();
    NewsAdapter newsAdapter;
    RecyclerView rvHeadline;
    NewsViewModel newsViewModel;
    TextView tvKeyword;
    Button btSearch;
    List<NewsArticle> newsArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        tvKeyword = findViewById(R.id.tvSearch);
        btSearch = findViewById(R.id.btSearch);

        newsViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(NewsViewModel.class);
//        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
//        newsViewModel.init();
//        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
//            newsArticles = newsResponse.getArticles();
//            articleArrayList.addAll(newsArticles);
//            newsAdapter.notifyDataSetChanged();
//        });
//
//        setupRecyclerView();

        NewsFragment fragobj = new NewsFragment();
        loadFragment(fragobj);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                newsViewModel.init(tvKeyword.getText().toString());
//                Log.d("Terclick", tvKeyword.getText().toString());
//                newsAdapter.notifyDataSetChanged();
                NewsFragment fragobj = new NewsFragment();
                loadFragment(fragobj);
            }
        });
    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(MainActivity.this, articleArrayList);
            rvHeadline.setLayoutManager(new LinearLayoutManager(this));
            rvHeadline.setAdapter(newsAdapter);
            rvHeadline.setItemAnimator(new DefaultItemAnimator());
            rvHeadline.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }

    private void loadFragment(Fragment fragment){
        Bundle argument = new Bundle();
        argument.putString("keyword", tvKeyword.getText().toString());
        fragment.setArguments(argument);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}