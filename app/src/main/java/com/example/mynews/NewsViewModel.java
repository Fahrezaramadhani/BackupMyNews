package com.example.mynews;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NewsViewModel extends ViewModel {
    private LiveData<NewsResponse> LiveData;
    private NewsRepository newsRepository;
    private static String KEY = "3b35b7950af94d8da344750275f9f4c8";

    public MutableLiveData<NewsResponse> getNewsEverythingFilter(String keyword){
        newsRepository = NewsRepository.getInstance();
//        mutableLiveData = newsRepository.getNews("id", "3b35b7950af94d8da344750275f9f4c8");
        return newsRepository.getNewsEverythingFilter(keyword, KEY);
    }

    public void init(){
        if (LiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
//        mutableLiveData = newsRepository.getNews("id", "3b35b7950af94d8da344750275f9f4c8");
        LiveData = newsRepository.getNews("id", KEY);

    }

    public LiveData<NewsResponse> getNewsRepository() {

        return LiveData;
    }
}
