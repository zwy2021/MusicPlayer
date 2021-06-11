package com.example.musicplayer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.musicplayer.BaseActivity;
import com.example.musicplayer.R;

public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    private void initView() {
        initNavBar(true,"修改密码",false);
    }
}