package com.wmc.usercenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvp.view.BaseActivity;

public class RegisterActivity extends BaseActivity {
    private ImageView registerLogo;
    private EditText registerUsername;
    private EditText registerPassword;
    private ImageView registerFinish;
    private ImageView registerBack;
    private ConstraintLayout registerParent;

    @Override
    protected void bindView() {
        registerLogo = findViewById(R.id.register_logo);
        registerUsername = findViewById(R.id.register_username);
        registerPassword = findViewById(R.id.register_password);
        registerFinish = findViewById(R.id.register_finish);
        registerBack = findViewById(R.id.register_back);
        registerParent = findViewById(R.id.register_parent);

    }

    @Override
    protected void initData() {
        //防止输入框获得焦点
        registerParent.setFocusableInTouchMode(true);
        registerParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerParent.setFocusable(true);
            }
        });
        //LOGO的点击事件
        registerLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLogo.setFocusable(true);
                Toast.makeText(RegisterActivity.this, "我不动让你们裸绞，我唱着歌让你们裸绞       ——混元太极马掌门", Toast.LENGTH_SHORT).show();
            }
        });
        //左上角返回
        registerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击注册的业务逻辑
        registerFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFinish.setFocusable(true);
                
                if (registerUsername.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "马家功夫没有套路,只有散手。你的用户名也一样", Toast.LENGTH_SHORT).show();
                }else if (registerPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "我上来就是一套松果弹抖闪电鞭，这就是你输的密码？", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
        


    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }
}