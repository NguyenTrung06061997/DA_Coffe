package com.example.myapplication.data.api.news;

import com.example.myapplication.news.data.News;

import io.reactivex.Observable;

public interface IAppRepositoryNews {
    Observable<News> getNews();
}
