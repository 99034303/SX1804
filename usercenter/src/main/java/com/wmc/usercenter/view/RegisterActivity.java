package com.wmc.usercenter.view;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.view.BaseActivity;
import com.example.mvp.view.BaseMVPActivity;
import com.example.net.BaseEntity;
import com.wmc.usercenter.R;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

@Route(path = "/view/RegisterActivity")
public class RegisterActivity extends BaseMVPActivity<UserCenterPresenter> implements Contract.View {
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
    protected void createPresenter() {
        mPresenter=new UserCenterPresenter(this);
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
                }else {
                    mPresenter.register(new RequestEntity(registerUsername.getText().toString().trim(),registerPassword.getText().toString().trim()));
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, "😒不知道该点谁嘛？？？", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void updateLoginUI(BaseEntity<LoginEntity> baseEntity) {

    }

    /**
     * 注册响应
     * @param baseEntity
     */
    @Override
    public void updateRegisterUI(BaseEntity<Boolean> baseEntity) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("username",registerUsername.getText().toString().trim());
        intent.putExtra("password",registerPassword.getText().toString().trim());
        setResult(102,intent);
        finish();
    }
}