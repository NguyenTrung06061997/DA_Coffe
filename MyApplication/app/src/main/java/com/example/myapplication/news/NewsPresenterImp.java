package com.example.myapplication.news;

import android.util.Log;

import com.example.myapplication.data.api.news.ApiHandlerNews;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenterImp implements INewsPresenter {
    private INewsView iNewsView;

    public NewsPresenterImp(INewsView iNewsView) {
        this.iNewsView = iNewsView;
    }

    @Override
    public void getNews() {
        ApiHandlerNews.getInstance()
                .getAppApi().getNews()
                .subscribeOn(Schedulers.io())
                .flatMap(news -> Observable.fromIterable(news.getObjects()))
                .toList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objectsItems -> {
                    iNewsView.displayNews(objectsItems);
                            Log.d( "getNews: ", objectsItems.toString());
                        },
                        throwable -> Log.d( "getNews: ", throwable +""));
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {

    }


}
