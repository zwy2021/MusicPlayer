package com.example.musicplayer.helps;

import com.example.musicplayer.models.UserModel;

import java.util.List;

import io.realm.Realm;
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
}
