package com.example.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;

import com.example.musicplayer.activities.MeActivity;

//描述Activity类的共性，是项目中所有Activity类的父类
public class BaseActivity extends Activity {
    private ImageView mIvBack,mIvMe;
    private TextView mTvTitle;

    //使用泛型，规定上限为View
    protected <T extends View> T fd(@IdRes int id){
        return findViewById(id);
    }

    /**
     *
     *
     *
     * @param isShowBack:back按钮显示与否
     * @param title:页面名称
     * @param isShowMe 最右侧的“我的”图标显示与否
     */
    protected void initNavBar(boolean isShowBack,String title,boolean isShowMe){
        mIvBack=findViewById(R.id.iv_back);
        mTvTitle =findViewById(R.id.tv_title);
        mIvMe=findViewById(R.id.rv_me);

        mIvBack.setVisibility(isShowBack? View.VISIBLE:View.GONE);
        mIvMe.setVisibility(isShowMe? View.VISIBLE:View.GONE);
        mTvTitle.setText(title);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mIvMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this, MeActivity.class));
            }
        });

    }
}
