package com.example.myapplication.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.news.data.ObjectsItem;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapterNews extends RecyclerView.Adapter<NewsAdapterNews.MyViewHolder> {
    private Context mContext;
    private List<ObjectsItem> mNewsList;
    private FragmentManager manager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title_for_news, title_bold_for_news;
        private ImageView thumbnail_news;

        public MyViewHolder(View view) {
            super(view);
            title_bold_for_news = view.findViewById(R.id.conten_news);
            thumbnail_news = view.findViewById(R.id.img_news);
            title_for_news = view.findViewById(R.id.title_news);
        }
    }

    public NewsAdapterNews(Context mContext, List<ObjectsItem> mNewList, FragmentManager manager) {
        this.mContext = mContext;
        this.mNewsList = mNewList;
        this.manager = manager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ObjectsItem news = mNewsList.get(position);
        holder.title_for_news.setText((news.getDescriptions()));
        holder.title_bold_for_news.setText(news.getTitle());
        Glide.with(mContext).load(news.getImages()).placeholder(R.drawable.anh1).into(holder.thumbnail_news);
//        holder.thumbnail_news.setOnClickListener(v -> {
//            try {
//                WebViewNews.newInstance(album).show(manager, "NewsDetail");
//            } catch (Exception e) {
//
//            }
//        });
//        holder.itemView.setOnClickListener(v -> {
//            try {
//                WebViewNews.newInstance(album).show(manager, "NewsDetail");
//            } catch (Exception e) {
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setValues(List<ObjectsItem> values) {
        mNewsList = values;
        notifyDataSetChanged();
    }
}
