package com.example.musicplayer;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.adapters.MusicGridAdapter;

public class MainActivity extends BaseActivity {

    private RecyclerView mRvGrid;

    private MusicGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        initNavBar(false,"音乐",true);
        mRvGrid=fd(R.id.rv_grid);
        mRvGrid.setLayoutManager(new GridLayoutManager(this,3));
        mAdapter= new MusicGridAdapter(this);

        mRvGrid.setAdapter(mAdapter);
    }
}