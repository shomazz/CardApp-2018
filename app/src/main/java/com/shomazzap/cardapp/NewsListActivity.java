package com.shomazzap.cardapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.shomazzap.cardapp.Data.DataUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class NewsListActivity extends AppCompatActivity {

    private final NewsRecyclerAdapter.OnItemClickListener clickListener = newsItem ->
            NewsDetailsActivity.start(this, newsItem);

    @BindView(R.id.recycler)
    RecyclerView newsList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        if (toolbar != null) setSupportActionBar(toolbar);
        else Log.w("Toolbar!", "Toolbar is NULL!");

        newsList.addItemDecoration(new ItemDecoration(this, R.dimen.recycler_item_margin));
        newsList.setAdapter(new NewsRecyclerAdapter(DataUtils.generateNews(), this, clickListener));

        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            final int columnsCount = 2;
            newsList.setLayoutManager(new GridLayoutManager(this, columnsCount));
        } else
            newsList.setLayoutManager(new LinearLayoutManager(this));
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