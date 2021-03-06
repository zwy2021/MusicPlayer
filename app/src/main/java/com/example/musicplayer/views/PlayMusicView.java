package com.example.musicplayer.views;

import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.musicplayer.R;
import com.example.musicplayer.helps.MediaPlayHelp;

public class PlayMusicView extends FrameLayout {
    private Context mContext;
    private View mView;
    private String mPath;
    private boolean isPlaying;
    private MediaPlayHelp mMediaPlayerHelp;
    private Animation mPlayMusicAnim,mPlayNeedleAnim,mStopNeedleAnim;
    private ImageView mIvIcon,mIvNeedle,mIvPlay;
    private FrameLayout mFlPlayMusic;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);

    }

    private void init(Context context){
        mContext = context;

        mView=LayoutInflater.from(mContext).inflate(R.layout.play_music,this,false);

        mFlPlayMusic=mView.findViewById(R.id.fl_play_music);
        mFlPlayMusic.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });

        mIvIcon=mView.findViewById(R.id.iv_icon);

        mIvNeedle=mView.findViewById(R.id.iv_needle);
        mIvPlay=mView.findViewById(R.id.iv_play);
        mPlayMusicAnim=AnimationUtils.loadAnimation(mContext,R.anim.play_music_animation);
        mPlayNeedleAnim=AnimationUtils.loadAnimation(mContext,R.anim.play_needle_anim);
        mStopNeedleAnim=AnimationUtils.loadAnimation(mContext,R.anim.stop_needle_anim);


        addView(mView);

        mMediaPlayerHelp=MediaPlayHelp.getInstance(mContext);
    }

    private void trigger(){
        if(isPlaying){
            stopMusic();
        }else{
            playMusic(mPath);
        }
    }

    public void playMusic(String path){
        mPath=path;
        isPlaying=true;
        mIvPlay.setVisibility(View.GONE);
        mFlPlayMusic.startAnimation(mPlayMusicAnim);
        mIvNeedle.startAnimation(mPlayNeedleAnim);

        if(mMediaPlayerHelp.getPath()!=null&&mMediaPlayerHelp.getPath().equals(path)){
            mMediaPlayerHelp.start();
        }else{
            mMediaPlayerHelp.setPath(path);
            mMediaPlayerHelp.setOnMediaPlayerHelperListener(new MediaPlayHelp.OnMediaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayerHelp.start();
                }
            });
        }
    }

    public void stopMusic(){
        isPlaying=false;
        mIvPlay.setVisibility(View.VISIBLE);
        mFlPlayMusic.clearAnimation();
        mIvNeedle.startAnimation(mStopNeedleAnim);

        mMediaPlayerHelp.pause();
    }


//????????????image
    public void setMusicIcon(String icon){
        Glide.with(mContext)
                .load(icon)
                .into(mIvIcon);
    }
}
