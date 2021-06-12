package com.example.musicplayer.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayHelp {

    public static MediaPlayHelp instance;

    private Context mContext;

    private MediaPlayer mMediaPlayer;
    private String mPath;

    private OnMediaPlayerHelperListener onMediaPlayerHelperListener;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        this.onMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    public static MediaPlayHelp getInstance(Context context){
        if(instance==null){
            synchronized (MediaPlayHelp.class){
                if(instance==null){
                    instance=new MediaPlayHelp(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayHelp(Context context){
        mContext=context;
        mMediaPlayer=new MediaPlayer();
    }

    public void setPath(String path){

        mPath=path;
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }

        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(onMediaPlayerHelperListener!=null){
                    onMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
    }

    public String getPath(){
        return mPath;
    }
    public  void start(){
        if(mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    public void pause(){
        mMediaPlayer.pause();
    }
    public interface OnMediaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
    }

}
