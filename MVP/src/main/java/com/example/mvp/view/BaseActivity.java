package com.example.mvp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements IView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bindView();
        initView();
        initData();
    }

    protected abstract void bindView();


    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();

    //吐司方法
    public void showMsg(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }
}
