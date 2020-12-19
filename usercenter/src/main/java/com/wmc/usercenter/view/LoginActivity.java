package com.wmc.usercenter.view;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.view.BaseMVPActivity;
import com.example.net.BaseEntity;
import com.wmc.sp.SPUtils;
import com.wmc.usercenter.R;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.RequestEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

import java.util.List;

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
    private boolean isRememberPwd=false;
    private boolean isAutoLogin=false;
    private SPUtils usersp;

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
        usersp = SPUtils.getInstance("user", this);
        isAutoLogin = (Boolean) usersp.get("isAuto", false);
        isRememberPwd = (Boolean) usersp.get("isRemember", false);
        if (isAutoLogin){
            mPresenter.login(new RequestEntity((String) usersp.get("username",""),(String)usersp.get("password","")));
        }
        //设置上次登录时的状态
        loginRemember.setChecked(isRememberPwd);
        loginAuto.setChecked(isAutoLogin);
        //记住密码直接输入用户名和密码
        if (isRememberPwd){
            loginUsername.setText((CharSequence) usersp.get("username",""));
            loginPassword.setText((CharSequence) usersp.get("password",""));
        }

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
                startActivityForResult(new Intent(LoginActivity.this,RegisterActivity.class),101);
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
                    loginFinish.setEnabled(false);
                    mPresenter.login(new RequestEntity(loginUsername.getText().toString().trim(),loginPassword.getText().toString().trim()));
                }
            }
        });
        //记住密码
        loginRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRememberPwd=isChecked;
                if (!isChecked){
                    loginAuto.setChecked(false);
                }
            }
        });
        //自动登录
        loginAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    loginRemember.setChecked(true);
                }
                isAutoLogin=isChecked;
            }
        });

        //忘记密码
        loginForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/view/ForgetActivity").navigation();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==102&&data!=null){
            loginUsername.setText(data.getStringExtra("username"));
            loginPassword.setText(data.getStringExtra("password"));
        }

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
            //存入用户id，用于后续的密码修改
            spUtils.put("uid",loginEntity.getId());
            //存入用户名
            spUtils.put("username",loginEntity.getPhonenumber());
            //存入token值
            spUtils.put("token",loginEntity.getToken());
            showMsg("登录成功");
            //判断是否需要记住密码
            if (isRememberPwd){
                usersp.put("username",loginUsername.getText().toString().trim());
                usersp.put("password",loginPassword.getText().toString().trim());
                usersp.put("isRemember",true);
            }else {
                usersp.deleteAll();
            }
            //判断是否自动登录
            if (isAutoLogin){
                usersp.put("isAuto",true);
            }else {
                usersp.put("isAuto",false);
            }
            ARouter.getInstance().build("/home/HomeActivity").navigation();
            finish();
        }else if (baseEntity.getCode() == -1){
            showMsg(baseEntity.getMsg());
            loginUsername.getText().clear();
            loginPassword.getText().clear();
            loginFinish.setEnabled(true);
        }
    }

    @Override
    public void updateRegisterUI(BaseEntity<Boolean> baseEntity) {

    }

    @Override
    public void ForgetCode(String code) {

    }

    @Override
    public void ForgetChange(boolean flag) {

    }

    @Override
    public void getFriend(List<FriendEntity> friendEntity) {

    }
    public void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result) {

    }

    @Override
    public void AddFriend(BaseEntity<Boolean> baseEntity) {

    }
}