package com.example.myapplication.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.order.DataItem;
import com.example.myapplication.order.Loading.ILoadMore;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0, VIEW_TYPE_LOADING = 1;
    private Context mContext;
    private List<DataItem> mListValues, mFilterList;
    private OnOrderListItemInteractionListener mListener;
    private ILoadMore loadMore;
    private Boolean isLoading = false;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;


    public OrderProductAdapter(Context mContext, List<DataItem> mListValues,RecyclerView recyclerView) {
        this.mContext = mContext;
        this.mListValues = mListValues;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount(); // Lấy tổng số lượng item đang có
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition(); // Lấy vị trí của item cuối cùng
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) // Nếu không phải trạng thái loading và tổng số lượng item bé hơn hoặc bằng vị trí item cuối + số lượng item tối đa hiển thị
                {
                    if (loadMore != null)
                        loadMore.onLoadMore(); // Gọi callback interface Loadmore
                    isLoading = true;
                }
            }
        });
    }
    @Override
    public int getItemViewType(int position) {
        return mListValues.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;// So sánh nếu item được get tại vị trí này là null thì view đó là loading view , ngược lại là item
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_product, parent, false);
            final OrderProductViewHolder holder = new OrderProductViewHolder(view);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "" + mListValues.get(holder.getPosition()).getProductName(), Toast.LENGTH_SHORT).show();
                }
            });
            return holder;
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_loading, parent, false);
            return new OrderLoadingViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof OrderProductViewHolder) {
            DataItem product = mListValues.get (position);
            OrderProductViewHolder viewHolder = (OrderProductViewHolder) holder;
            viewHolder.bindToViewHolder(product);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(product);
            }
        });
        } else if (holder instanceof OrderLoadingViewHolder) {
            OrderLoadingViewHolder loadingViewHolder = (OrderLoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }
    @Override
    public int getItemCount() {
        return mListValues.size ();
    }

    public void setValues(List<DataItem> values) {
        mListValues.clear ();
        mListValues.addAll (values);
        notifyDataSetChanged ();
    }


    public void setListener(OnOrderListItemInteractionListener mListener) {
        this.mListener = mListener;
    }
    public void setLoader() {
        isLoading = false;
    }
}
