package com.example.myapplication.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.news.adapter.NewsAdapterNews;
import com.example.myapplication.news.data.ObjectsItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NewsFragment extends Fragment implements INewsView {
    private List<ObjectsItem> list;
    private RecyclerView recyclerView;
    private NewsAdapterNews newsAdapterNews;
    private NewsPresenterImp newsPresenterImp;

    private AppCompatActivity activity;

    public static Fragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.news);
        newsAdapterNews = new NewsAdapterNews(getContext(),list,getFragmentManager());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(newsAdapterNews);
        setupNews();
    }

    private void setupNews() {
        newsPresenterImp = new NewsPresenterImp(this);

    }

    private void getNewList() {
        newsPresenterImp.getNews();
    }

    private void Data() {

    }

    @Override
    public void displayNews(List<ObjectsItem> newsReponseList) {
        if (newsReponseList != null) {
            newsAdapterNews.setValues(newsReponseList);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getNewList();
    }

    @Override
    public void onError(Throwable throwable) {
        Toast.makeText(activity, "Something wrong! Try a gain.", Toast.LENGTH_SHORT).show();
    }
}
