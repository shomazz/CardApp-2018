package com.shomazzap.cardapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.shomazzap.cardapp.Data.NewsItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends AppCompatActivity {

    private static final String TAG_EXTRA = "news_item";

    @BindView(R.id.news_details_image)
    ImageView newsImage;
    @BindView(R.id.news_details_title)
    TextView newsTtile;
    @BindView(R.id.news_details_date)
    TextView newsDate;
    @BindView(R.id.news_details_content)
    TextView newsContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    NewsItem newsItem;

    public static void start(@NonNull Context context, @NonNull NewsItem newsItem) {
        context.startActivity(new Intent(context, NewsDetailsActivity.class)
                .putExtra(TAG_EXTRA, newsItem));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);

        if (toolbar != null)
            setSupportActionBar(toolbar);
        else
            Log.w("Toolbar!", "Toolbar is NULL!");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(onClick -> onBackPressed());
        //else Toast.makeText(this, "Toolbar is NULL!", Toast.LENGTH_SHORT).show();

        newsItem = (NewsItem) getIntent().getSerializableExtra(TAG_EXTRA);
        fillContent(newsItem);
    }

    private void fillContent(NewsItem item) {
        newsContent.setText(item.getFullText());
        newsDate.setText(item.getFullPublishDate());
        collapsingToolbar.setTitle(item.getShortTitle());
        newsTtile.setText(item.getTitle());
        Glide.with(this)
                .load(item.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(newsImage);
    }

}
