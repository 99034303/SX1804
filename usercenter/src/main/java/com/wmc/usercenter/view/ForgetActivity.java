package com.wmc.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvp.view.BaseMVPActivity;
import com.example.net.BaseEntity;
import com.wmc.imageloader.ImageUtils;
import com.wmc.usercenter.R;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

import java.util.List;

@Route(path = "/view/ForgetActivity")
public class ForgetActivity extends BaseMVPActivity<UserCenterPresenter> implements Contract.View {
    private RelativeLayout forgetParent;
    private ImageView forgetBackground;
    private ImageView forgetTitle;
    private EditText forgetUsername;
    private EditText forgetPassword;
    private Button forgetFinish;
    private EditText forgetCode;
    private Button forgetCodeBtn;


    @Override
    protected void bindView() {
        forgetParent = findViewById(R.id.forget_parent);
        forgetBackground = findViewById(R.id.forget_background);
        forgetTitle = findViewById(R.id.forget_title);
        forgetUsername = findViewById(R.id.forget_username);
        forgetPassword = findViewById(R.id.forget_password);
        forgetFinish = findViewById(R.id.forget_finish);
        forgetCode = findViewById(R.id.forget_code);
        forgetCodeBtn = findViewById(R.id.forget_code_btn);
    }

    @Override
    protected void createPresenter() {
        mPresenter=new UserCenterPresenter(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ImageUtils.getInstance(ImageUtils.GLIDE).glideGif(this,"https://c-ssl.duitang.com/uploads/item/202002/17/20200217164048_AUwJP.thumb.1000_0.gif",forgetBackground);
        forgetParent.setFocusableInTouchMode(true);
        //父布局获取焦点
        forgetParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetParent.setFocusable(true);
            }
        });
        //背景图隐藏和开启
        forgetTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forgetBackground.getVisibility()==View.VISIBLE){
                    forgetBackground.setVisibility(View.INVISIBLE);
                }else {
                    forgetBackground.setVisibility(View.VISIBLE);
                }
            }
        });
        //获取验证码
        forgetCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.forgetCode();
            }
        });
//        //完成按钮
//        forgetFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //不能再次点击
//                forgetFinish.setEnabled(false);
//
//
//
//                mPresenter.forgetChange();
//            }
//        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void updateLoginUI(BaseEntity<LoginEntity> baseEntity) {

    }

    @Override
    public void updateRegisterUI(BaseEntity<Boolean> baseEntity) {

    }

    @Override
    public void ForgetCode(String code) {
        forgetCode.setText(code);
    }

    @Override
    public void ForgetChange(boolean flag) {
        if (flag){
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        //恢复可点击事件
        forgetFinish.setEnabled(true);
    }

    @Override
    public void getFriend(List<FriendEntity> friendEntity) {

    }
}