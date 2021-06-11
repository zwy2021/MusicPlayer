package com.example.musicplayer;

import android.os.Bundle;
import com.example.musicplayer.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        initNavBar(false,"音乐",true);
    }
}