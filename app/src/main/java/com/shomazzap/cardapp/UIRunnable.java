package com.shomazzap.cardapp;

import com.shomazzap.cardapp.Data.NewsItem;
import com.shomazzap.cardapp.Util.Utils;

import java.lang.ref.WeakReference;
import java.util.List;

public class UIRunnable implements Runnable {

    private final WeakReference<ILoadingView> loadingViewRef;
    private final WeakReference<List<NewsItem>> newsItemsRef;

    public UIRunnable(ILoadingView loadingView, List<NewsItem> newsItems) {
        loadingViewRef = new WeakReference<>(loadingView);
        newsItemsRef = new WeakReference<>(newsItems);
    }

    @Override
    public void run() {
        Utils.log("uiRunnable started!");
        ILoadingView loadingView = loadingViewRef.get();
        if (loadingView != null) {
            Utils.log("Try to update news...");
            loadingView.updateItems(newsItemsRef.get());
        }
        Utils.log("uiRunnable stopped!");
    }
}
