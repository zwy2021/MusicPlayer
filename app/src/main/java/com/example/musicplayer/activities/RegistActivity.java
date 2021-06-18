package com.example.musicplayer.activities;

import android.os.Bundle;
import android.view.View;

import com.example.musicplayer.BaseActivity;
import com.example.musicplayer.R;
import com.example.musicplayer.utils.UserUtil;
import com.example.musicplayer.views.InputView;

public class RegistActivity extends BaseActivity {
    private InputView mInputPhone,mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        initView();
    }

    private void initView() {
        initNavBar(true,"注册",false);
        mInputPhone=fd(R.id.input_phone);
        mInputPassword=fd(R.id.input_password);
        mInputPasswordConfirm =fd(R.id.input_password_confirm);
    }

    /**
     * 检验输入合法性：手机号合法&密码&确认密码合法&手机号未被注册
     * 保存输入的手机号和密码
     */
    public void onRegisterClick(View v){
        String phone = mInputPhone.getInputStr();
        String password = mInputPassword.getInputStr();
        String passwordConfirm = mInputPasswordConfirm.getInputStr();

        boolean result = UserUtil.registerUser(this, phone, password, passwordConfirm);
//注册不成功，直接返回
        if (!result) return;
//注册成功，后退页面
        onBackPressed();
    }
}