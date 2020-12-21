package com.example.mvp.view;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp.presenter.BasePresenter;

public abstract class BaseMVPActivity<P extends BasePresenter> extends AppCompatActivity implements IView {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        requestPermissions(new String[]{
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.ACCESS_NETWORK_STATE",
                "android.permission.ACCESS_WIFI_STATE",
                "android.permission.READ_PHONE_STATE",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.ACCESS_FINE_LOCATION"

        }, 100);
        createPresenter();
        bindView();
        initView();
        initData();
    }

    protected abstract void bindView();

    protected abstract void createPresenter();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();
    //吐司方法
    public void showMsg(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
