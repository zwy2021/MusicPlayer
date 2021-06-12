package com.example.musicplayer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer.BaseActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {

    private PlayMusicView mPlayMusicView;
    private ImageView mIvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }

    private void initView(){
        mIvBg=fd(R.id.iv_bg);
        //高斯模糊
        Glide.with(this)
                .load("https://img9.doubanio.com/view/group_topic/raw/public/p250046415.jpg")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(mIvBg);
        mPlayMusicView=fd(R.id.play_music_view);
        mPlayMusicView.setMusicIcon("https://img9.doubanio.com/view/group_topic/raw/public/p250046415.jpg");
        mPlayMusicView.playMusic("https://link.jscdn.cn/sharepoint/aHR0cHM6Ly8xZHJpdi1teS5zaGFyZXBvaW50LmNvbS86dTovZy9wZXJzb25hbC9zdG9yXzFkcml2X29ubWljcm9zb2Z0X2NvbS9FVjlFM09lOF81aEF0Zjd6S2dSVHdxQUJXcEJuYXpLWXhVUTQtd2M2LUg1emJn.mp3");

    }
    public void onBackClick(View view){
        onBackPressed();
    }
}