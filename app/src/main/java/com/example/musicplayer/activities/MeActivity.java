package com.example.musicplayer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicplayer.BaseActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.utils.UserUtil;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        initView();
    }

    private void initView() {
        initNavBar(true,"个人中心",false);
    }
/**
 * 修改密码
 */
    public void onChangeClick(View v){
        startActivity(new Intent(this,ChangePasswordActivity.class));
    }

    /**
     * 退出登录
     */
    public void onLogoutClick(View v){
        UserUtil.logout(this);
    }
}