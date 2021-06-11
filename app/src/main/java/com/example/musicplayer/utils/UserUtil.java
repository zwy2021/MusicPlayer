package com.example.musicplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;
import com.example.musicplayer.R;
import com.example.musicplayer.activities.LoginActivity;

public class UserUtil {
    /**
     * 登录输入合法性验证（账号密码）
     */
    public static boolean validateLogin(Context context,String phone,String password){
        if(!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /**
     * 退出登录
     */
    public static void logout(Context context){
        Intent intent=new Intent(context, LoginActivity.class);
        //清理intent标志符，清理task栈，并生成新的task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //保证动画不因为清理task栈发生错乱
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }
}
