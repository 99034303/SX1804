package com.wmc.usercenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvp.view.BaseActivity;

public class LoginActivity extends BaseActivity {
    private ConstraintLayout loginParent;
    private ImageView loginLogo;
    private ImageView imgRegister;
    private EditText loginUsername;
    private EditText loginPassword;
    private ImageView loginFinish;
    private ImageView loginBack;

    @Override
    protected void bindView() {
        loginParent = findViewById(R.id.login_parent);
        loginLogo = findViewById(R.id.login_logo);
        imgRegister = findViewById(R.id.img_register);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginFinish = findViewById(R.id.login_finish);
        loginBack = findViewById(R.id.login_back);

    }

    @Override
    protected void
    initData() {

    }

    @Override
    protected void initView() {
        loginParent.setFocusableInTouchMode(true);
        loginParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginParent.setFocusable(true);
            }
        });


        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        loginLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLogo.setFocusable(true);
                Toast.makeText(LoginActivity.this, "如有意见反馈，请联系混元太极马掌门。", Toast.LENGTH_SHORT).show();
            }
        });

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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}