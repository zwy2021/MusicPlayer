package com.example.musicplayer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.example.musicplayer.R;
import com.example.musicplayer.activities.LoginActivity;
import com.example.musicplayer.helps.RealmHelper;
import com.example.musicplayer.helps.UserHelper;
import com.example.musicplayer.models.UserModel;

import java.util.List;

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
        if (password.length()<6){
            Toast.makeText(context,"密码长度至少为六位",Toast.LENGTH_SHORT).show();
            return false;
        }
        /**
         * 注册时账户已存在检验
         *
         */
        if(!UserUtil.userExistFromPhone(phone)){
            Toast.makeText(context,"手机号未被注册",Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelper realmHelper = new RealmHelper();
        boolean result = realmHelper.validateUser(phone, EncryptUtils.encryptMD5ToString(password));
        if (!result){
            Toast.makeText(context,"手机号或密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean isSave=SPUtils.saveUser(context,phone);
        if(!isSave){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return false;
        }
//在全局单例里保存用户标记
        UserHelper.getInstance().setPhone(phone);

        realmHelper.setMusicSource(context);
        realmHelper.close();

        return true;
    }
    /**
     * 退出登录
     */
    public static void logout(Context context){
        boolean isRemove = SPUtils.removeUser(context);
        if(!isRemove){
            Toast.makeText(context,"系统错误，请稍后重试",Toast.LENGTH_SHORT).show();
            return;
        }

        RealmHelper realmHelper = new RealmHelper();
        realmHelper.removeMusicSource();
        realmHelper.close();
        Intent intent=new Intent(context, LoginActivity.class);
        //清理intent标志符，清理task栈，并生成新的task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //保证动画不因为清理task栈发生错乱
        ((Activity)context).overridePendingTransition(R.anim.open_enter,R.anim.open_exit);
    }

    /**
     * 用户注册
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context,String phone, String password,String passwordConfirm){
        //手机号有效性检验
        if (!RegexUtils.isMobileExact(phone)){
            Toast.makeText(context,"无效手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        //密码有效性检验
        if(StringUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        //手机号未被注册检验
        if (UserUtil.userExistFromPhone(phone)){
            Toast.makeText(context,"该手机号已存在",Toast.LENGTH_SHORT).show();

            return false;
        }
        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));

        saveUser(userModel);
        return true;
    }

    /**
     * 保存用户到数据库
     * @param userModel
     */
    public static void saveUser(UserModel userModel){
        RealmHelper realmHelper = new RealmHelper();
        realmHelper.saveUser(userModel);
        realmHelper.close();
    }

    public static boolean userExistFromPhone(String phone){
        boolean result=false;

        RealmHelper realmHelper = new RealmHelper();
        List<UserModel> allUser = realmHelper.getAllUser();
        for (UserModel userModel : allUser) {
            if(userModel.getPhone().equals(phone)){
                result=true;
                break;
            }
        }
        return result;
    }
    public static boolean validateUserLogin(Context context){
        return SPUtils.isLoginUser(context);
    }

    /**
     * 修改密码验证
     * 1.原密码正确输入
     * 2.新密码输入且与确定密码相同
     */
    public static boolean changePassword(Context context, String oldPassword, String password,String passwordConfirm){
        if(TextUtils.isEmpty(oldPassword)){
            Toast.makeText(context,"请输入原密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(password)||!password.equals(passwordConfirm)){
            Toast.makeText(context,"请确认密码",Toast.LENGTH_SHORT).show();
            return false;
        }
        RealmHelper realmHelper = new RealmHelper();
        UserModel userModel = realmHelper.getUser();

        if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())){
            Toast.makeText(context,"原密码不正确",Toast.LENGTH_SHORT).show();
            return false;
        }
        realmHelper.changePassword(EncryptUtils.encryptMD5ToString(password));
        realmHelper.close();
        return true;
    }
}
