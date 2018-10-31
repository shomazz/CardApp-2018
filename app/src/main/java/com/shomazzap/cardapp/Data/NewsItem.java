package com.shomazzap.cardapp.Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;

public class NewsItem implements Serializable {

    private final String title;
    private final String imageUrl;
    private final Category category;
    private final Date publishDate;
    private final String previewText;
    private final String fullText;
    private final String shortTitle;

    public NewsItem(String title, String imageUrl, Category category,
                    Date publishDate, String previewText, String fullText, String shortTitle) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.category = category;
        this.publishDate = publishDate;
        this.previewText = previewText;
        this.fullText = fullText;
        this.shortTitle = shortTitle;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " (" + getPublishDate() + ")";
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public String getPublishDate() {
        // Locale US, because all details on english
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM, HH:mm a", Locale.US);
        return dateFormat.format(publishDate);
    }

    public String getPreviewText() {
        return previewText;
    }

    public String getFullText() {
        return fullText;
    }

    public String getShortTitle() {
        return shortTitle;
    }
}