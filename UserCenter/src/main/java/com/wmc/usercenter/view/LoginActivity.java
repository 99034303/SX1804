package com.wmc.usercenter.view;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mvp.view.BaseMVPActivity;
import com.example.net.BaseEntity;
import com.wmc.sp.SPUtils;
import com.wmc.usercenter.R;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

public class LoginActivity extends BaseMVPActivity<UserCenterPresenter> implements Contract.View {
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
        mPresenter = new UserCenterPresenter(this);
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


    /**
     * 登录响应
     * @param baseEntity
     */
    @Override
    public void updateLoginUI(BaseEntity<LoginEntity> baseEntity) {
        if (baseEntity.getCode() == 0){
            LoginEntity loginEntity = baseEntity.getData();

            SPUtils spUtils = SPUtils.getInstance("gisim",this);
            spUtils.put("uid",loginEntity.getId());                         //存入用户id，用于后续的密码修改
            spUtils.put("username",loginEntity.getPhonenumber());           //存入用户名
            spUtils.put("token",loginEntity.getToken());                    //存入token值

            showMsg("登录成功");
        }else if (baseEntity.getCode() == -1){
            showMsg(baseEntity.getMsg());
        }
    }

    @Override
    public void updateRegisterUI(BaseEntity<Boolean> baseEntity) {

    }
}