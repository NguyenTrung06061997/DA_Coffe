package com.example.myapplication.data.api.news;

import com.example.myapplication.news.data.News;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AppApiNews {
    @GET("DoAnTN_Android/DoAnTN_Android/GetAllBlog.php")
    Observable<News> getNews();
}
