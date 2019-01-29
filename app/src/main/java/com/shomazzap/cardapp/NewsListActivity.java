package com.shomazzap.cardapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shomazzap.cardapp.Data.DataUtils;
import com.shomazzap.cardapp.Data.NewsItem;
import com.shomazzap.cardapp.Util.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class NewsListActivity extends AppCompatActivity implements ILoadingView {

    private final NewsRecyclerAdapter.OnItemClickListener clickListener = newsItem ->
            NewsDetailsActivity.start(this, newsItem);

    private NewsRecyclerAdapter newsAdapter;
    private Thread thread;

    @BindView(R.id.recycler)
    RecyclerView newsRecycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progress_bar)
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        if (toolbar != null)
            setSupportActionBar(toolbar);

        newsAdapter = new NewsRecyclerAdapter(this, clickListener);
        newsRecycler.setAdapter(newsAdapter);
        newsRecycler.addItemDecoration(new ItemDecoration(this, R.dimen.recycler_item_margin));

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE)
            newsRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        else
            newsRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNews();
    }

    private void loadNews() {
        Utils.log("Start thread...");
        thread = new Thread(new LoadNewsRunnable(new Handler(), this));
        thread.start();
        showProgress(true);
    }

    @Override
    public void updateItems(@Nullable List<NewsItem> news) {
        showProgress(false);
        if (newsAdapter != null) newsAdapter.replaceItems(news);
    }

    @Override
    public void showProgress(boolean shouldShow) {
        Utils.log("ShowProgress : Current Thread is " + Thread.currentThread().getName());
        Utils.log("Set Resycler " + (!shouldShow ? "visible" : "gone"));
        Utils.setVisible(newsRecycler, !shouldShow);
        Utils.log("Set Progress " + (shouldShow ? "visible" : "gone"));
        Utils.setVisible(progress, shouldShow);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (thread != null) {
            thread.interrupt();
        }
        thread = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toolbar = null;
        newsRecycler = null;
        progress = null;
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}