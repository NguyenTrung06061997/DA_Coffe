package com.example.myapplication.data.api.news;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHandlerNews {
    private static ApiHandlerNews sInstance;
    private AppApiNews mAppApi;
    private static final String BASE_URL = "https://trungwebda.000webhostapp.com/";

    private ApiHandlerNews() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(10,TimeUnit.SECONDS)
//                .writeTimeout(10,TimeUnit.SECONDS)
//                .build();
        mAppApi = new Retrofit
                .Builder()
//                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(AppApiNews.class);
    }

    public static ApiHandlerNews getInstance() {
        if (sInstance == null) {
            sInstance = new ApiHandlerNews();
        }
        return sInstance;
    }

    public AppApiNews getAppApi() {
        return mAppApi;
    }
}
