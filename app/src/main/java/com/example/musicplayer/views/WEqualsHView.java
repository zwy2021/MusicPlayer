package com.example.musicplayer.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class WEqualsHView extends AppCompatImageView {
    public WEqualsHView(@NonNull Context context) {
        super(context);
    }

    public WEqualsHView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualsHView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
