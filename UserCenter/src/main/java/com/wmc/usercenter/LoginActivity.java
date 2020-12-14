package com.wmc.usercenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvp.view.BaseActivity;
import com.example.mvp.view.BaseMVPActivity;

public class LoginActivity extends BaseMVPActivity {
    private ConstraintLayout loginParent;
    private ImageView loginLogo;
    private ImageView imgRegister;
    private EditText loginUsername;
    private EditText loginPassword;
    private ImageView loginFinish;
    private ImageView loginBack;
    private CheckBox loginRemember;
    private CheckBox loginAuto;
    private ImageView loginForget;


    @Override
    protected void bindView() {
        loginParent = findViewById(R.id.login_parent);
        loginLogo = findViewById(R.id.login_logo);
        imgRegister = findViewById(R.id.img_register);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginFinish = findViewById(R.id.login_finish);
        loginBack = findViewById(R.id.login_back);
        loginRemember = findViewById(R.id.login_remember);
        loginAuto = findViewById(R.id.login_auto);
        loginForget = findViewById(R.id.login_forget);

    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        loginParent.setFocusableInTouchMode(true);
        //获取焦点
        loginParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginParent.setFocusable(true);
            }
        });

        //退出
        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //注册点我
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        //LOGO
        loginLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLogo.setFocusable(true);
                Toast.makeText(LoginActivity.this, "如有意见反馈，请联系浑元形意太极拳掌门人。", Toast.LENGTH_SHORT).show();
            }
        });
        //登录
        loginFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginUsername.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "年轻人不讲武德，用户名有问题啊。", Toast.LENGTH_SHORT).show();
                }else if (loginPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this, "去骗去偷袭，密码你确定？", Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });
        //记住密码
        loginRemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //自动登录
        loginAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginAuto.isChecked()){
                    loginRemember.setChecked(true);
                }
            }
        });
        //忘记密码
        loginForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "看不见左上角？？？🤔", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}