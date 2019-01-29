package com.shomazzap.cardapp;

import android.os.Handler;

import com.shomazzap.cardapp.Data.DataUtils;
import com.shomazzap.cardapp.Data.NewsItem;
import com.shomazzap.cardapp.Util.Utils;

import java.lang.ref.WeakReference;
import java.util.List;

public class LoadNewsRunnable implements Runnable {

    private final WeakReference<Handler> handlerRef;
    private final WeakReference<ILoadingView> loadingViewRef;

    public LoadNewsRunnable(Handler handler, ILoadingView loadingView) {
        handlerRef = new WeakReference<>(handler);
        loadingViewRef = new WeakReference<>(loadingView);
    }

    @Override
    public void run() {
        Utils.log("Start generate news...");
        List<NewsItem> newsItems = DataUtils.generateNews();
        Utils.log("News generated!");
        if (Utils.isDebug()) {
            try {
                Utils.log("Wait fo 2 sec...");
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        if (Thread.interrupted()) return;
        Handler handler = handlerRef.get();
        Utils.log("Start uiRunnable...");
        if (handler != null) {
            handler.post(new UIRunnable(loadingViewRef.get(), newsItems));
        }
        Utils.log("News generating Thread stopped!");
    }
}