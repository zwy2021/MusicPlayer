package com.example.musicplayer.helps;

import android.content.Context;

import com.example.musicplayer.migration.Migration;
import com.example.musicplayer.models.AlbumModel;
import com.example.musicplayer.models.MusicModel;
import com.example.musicplayer.models.MusicSourceModel;
import com.example.musicplayer.models.UserModel;
import com.example.musicplayer.utils.DataUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmHelper {
    private Realm mRealm;

    /**
     * 关闭数据库
     */
    public void close(){
        if(mRealm!=null&&!mRealm.isClosed()){
            mRealm.close();
        }
    }
    public static void migration(){
        RealmConfiguration conf = getRealmConf();
        Realm.setDefaultConfiguration(conf);
        try {
            Realm.migrateRealm(conf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static RealmConfiguration getRealmConf(){
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration()).build();
    }
    public RealmHelper() {
        this.mRealm = Realm.getDefaultInstance();
    }
    /**
     * 保存用户信息
     */
    public void saveUser(UserModel userModel){
        mRealm.beginTransaction();
        mRealm.insert(userModel);
        mRealm.commitTransaction();
    }
    /**
     * 返回所有用户
     */
    public List<UserModel> getAllUser(){
        RealmQuery<UserModel> query =mRealm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }
    /**
     * 验证用户信息(账号密码匹配）
     */
    public boolean validateUser(String phone,String password){
        boolean result=false;
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        query=query.equalTo("phone",phone)
                .equalTo("password",password);
        UserModel userModel = query.findFirst();
        if(userModel!=null){
            result=true;
        }
        return result;
    }
    public UserModel getUser(){
        RealmQuery<UserModel> query = mRealm.where(UserModel.class);
        UserModel userModel = query.equalTo("phone", UserHelper.getInstance().getPhone()).findFirst();
        return userModel;
    }
    public void changePassword(String password){
        UserModel userModel = getUser();
        mRealm.beginTransaction();
        userModel.setPassword(password);
        mRealm.commitTransaction();
    }
    public void setMusicSource(Context context){
        String musicSourceJson = DataUtils.getJsonFromAssets(context, "DataSource.json");
        mRealm.beginTransaction();
        mRealm.createObjectFromJson(MusicSourceModel.class,musicSourceJson);
        mRealm.commitTransaction();
    }

    public void removeMusicSource(){
        mRealm.beginTransaction();
        mRealm.delete(MusicSourceModel.class);
        mRealm.delete(MusicModel.class);
        mRealm.delete(AlbumModel.class);
        mRealm.commitTransaction();
    }
    public MusicSourceModel getMusicSource(){
        return mRealm.where(MusicSourceModel.class).findFirst();
    }

    public AlbumModel getAlbum(String albumId){
        return mRealm.where(AlbumModel.class).equalTo("albumId",albumId).findFirst();
    }

    public MusicModel getMusic(String musicId){
        return mRealm.where(MusicModel.class).equalTo("musicId",musicId).findFirst();
    }
}
