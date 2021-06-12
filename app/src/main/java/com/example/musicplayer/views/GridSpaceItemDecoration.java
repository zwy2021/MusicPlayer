package com.example.musicplayer.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public GridSpaceItemDecoration(int space,RecyclerView parent) {
        mSpace=space;
        getRecyclerViewOffsrts(parent);
    }

    /**
     *
     * @param outRect Item矩形边界
     * @param view ItemView
     * @param parent RecyclerView
     * @param state RecyclerView的状态
     *
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left=mSpace;
    }
    private void getRecyclerViewOffsrts(RecyclerView parent){
        //margin为负解决首个item偏移问题
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
