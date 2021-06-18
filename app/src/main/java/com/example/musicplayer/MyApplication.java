package com.example.musicplayer;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;

//存储系统信息
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        Realm.init(this);
    }
}
