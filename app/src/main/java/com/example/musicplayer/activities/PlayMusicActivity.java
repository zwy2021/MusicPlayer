package com.example.musicplayer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.musicplayer.BaseActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.helps.RealmHelper;
import com.example.musicplayer.models.MusicModel;
import com.example.musicplayer.views.PlayMusicView;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class PlayMusicActivity extends BaseActivity {
    public static final String MUSIC_ID="musicId";
    private PlayMusicView mPlayMusicView;
    private ImageView mIvBg;
    private TextView mTvName,mTvAuthor;
    private String mMusicId;
    private RealmHelper mRealmHelper;
    private MusicModel mMusicModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initData();
        initView();

    }
    private void initData(){
        mMusicId=getIntent().getStringExtra(MUSIC_ID);
        mRealmHelper=new RealmHelper();
        mMusicModel = mRealmHelper.getMusic(mMusicId);
    }

    private void initView(){
        mIvBg=fd(R.id.iv_bg);
        mTvName=fd(R.id.tv_name);
        mTvAuthor=fd(R.id.tv_author);

        //高斯模糊
        Glide.with(this)
                .load(mMusicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25,10)))
                .into(mIvBg);
        mTvAuthor.setText(mMusicModel.getAuthor());
        mPlayMusicView=fd(R.id.play_music_view);
        mPlayMusicView.setMusicIcon(mMusicModel.getPoster());
        mPlayMusicView.playMusic(mMusicModel.getPath());

    }
    public void onBackClick(View view){
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}