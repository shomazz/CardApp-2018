package com.shomazzap.cardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shomazzap.cardapp.Data.NewsItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

    private RequestOptions imageOption = new RequestOptions()
            .placeholder(R.drawable.news_photo_placeholder)
            .fallback(R.drawable.news_photo_placeholder)
            .centerCrop();
    @NonNull
    private List<NewsItem> news;
    @Nullable
    private OnItemClickListener onClickListener;
    @NonNull
    private LayoutInflater inflater;

    public NewsRecyclerAdapter(@NonNull List<NewsItem> news,
                               @NonNull Context context,
                               @Nullable OnItemClickListener onClickListener) {
        this.news = news;
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.news_item, parent, false), onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem newsItem);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView newsPhoto;
        private final TextView newsTitle;
        private final TextView newsCategory;
        private final TextView newsContent;
        private final TextView newsDate;

        public ViewHolder(@NonNull View itemView, @Nullable OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(news.get(position));
            });
            newsPhoto = itemView.findViewById(R.id.news_item_photo);
            newsTitle = itemView.findViewById(R.id.news_item_title);
            newsCategory = itemView.findViewById(R.id.news_item_category);
            newsContent = itemView.findViewById(R.id.news_item_content);
            newsDate = itemView.findViewById(R.id.news_item_date);
        }

        public void bind(NewsItem newsItem) {
            Glide.with(newsPhoto).applyDefaultRequestOptions(imageOption)
                    .load(newsItem.getImageUrl())
                    .into(newsPhoto);
            newsTitle.setText(newsItem.getTitle());
            newsCategory.setText(newsItem.getCategory().getName());
            newsContent.setText(newsItem.getPreviewText());
            newsDate.setText(newsItem.getShortPublishDate());
        }
    }
}
