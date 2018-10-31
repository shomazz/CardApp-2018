package com.shomazzap.cardapp;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int itemOffset;

    public ItemDecoration(int itemOffset) {
        this.itemOffset = itemOffset;
    }

    public ItemDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {

        final int position = parent.getChildLayoutPosition(view);

        if (position != RecyclerView.NO_POSITION) {
            outRect.set(itemOffset, itemOffset, itemOffset, itemOffset);
        } else {
            outRect.set(0, 0, 0, 0);
        }
    }
}
