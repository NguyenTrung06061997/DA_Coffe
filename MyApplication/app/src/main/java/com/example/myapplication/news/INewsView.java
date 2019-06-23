package com.example.myapplication.news;

import android.app.Activity;

import com.example.myapplication.news.data.ObjectsItem;

import java.util.List;

public interface INewsView {
    void displayNews(List<ObjectsItem> newsReponseList);
    void onError(Throwable throwable);
    Activity getActivity();
}
