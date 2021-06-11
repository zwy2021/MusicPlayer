package com.example.musicplayer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.musicplayer.BaseActivity;
import com.example.musicplayer.MainActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.utils.UserUtil;
import com.example.musicplayer.views.InputView;

//NavigationBar
public class LoginActivity extends BaseActivity {

    private InputView mInputPhone,mInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView(){
        initNavBar(
                false,"登录",false);
        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
    }
    /**
     * 跳转注册页面点击事件
     */
    public void onRegisterClick(View v){
        Intent intent = new Intent(this,RegistActivity.class);
        startActivity(intent);
    }
    /**
     * 登录
     */
    public void onCommitClick(View v){
        String phone=mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        //验证
//        if(!UserUtil.validateLogin(this,phone,password)){
//            return;
//        }
        //验证成功，跳转到主页
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}