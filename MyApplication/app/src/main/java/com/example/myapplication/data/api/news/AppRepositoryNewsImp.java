package com.example.myapplication.data.api.news;



import com.example.myapplication.news.data.News;

import io.reactivex.Observable;

public class AppRepositoryNewsImp implements IAppRepositoryNews {

    @Override
    public Observable<News> getNews() {
        return ApiHandlerNews.getInstance().getAppApi().getNews();
    }
}
