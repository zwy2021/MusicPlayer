package com.example.musicplayer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.musicplayer.constants.SPConstants;
import com.example.musicplayer.helps.UserHelper;

/**
 * 用户登陆时，用SharedPreferences保存登录用户的用户标记
 */
public class SPUtils {
    public static boolean saveUser(Context context,String phone){
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SPConstants.SP_KEY_PHONE,phone);
        boolean result = editor.commit();
        return result;
    }
    public static boolean isLoginUser(Context context){
        boolean result=false;
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPConstants.SP_KEY_PHONE, "");
        if(!TextUtils.isEmpty(phone)){
            result=true;
            UserHelper.getInstance().setPhone(phone);
        }
        return result;
    }
    public static boolean removeUser(Context context){
        SharedPreferences sp = context.getSharedPreferences(SPConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(SPConstants.SP_KEY_PHONE);
        boolean result = editor.commit();
        return result;

    }
}
