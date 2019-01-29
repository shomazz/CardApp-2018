package com.shomazzap.cardapp;

import com.shomazzap.cardapp.Data.NewsItem;

import java.util.List;

public interface ILoadingView {

    void showProgress(boolean shouldShow);

    void updateItems(List<NewsItem> newsItems);
}