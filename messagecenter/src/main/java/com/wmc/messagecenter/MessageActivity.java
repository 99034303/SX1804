package com.wmc.messagecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.mvp.view.BaseMVPActivity;
import com.wmc.messagecenter.mvp.contract.UserCenter;
import com.wmc.messagecenter.mvp.model.entity.Entity;
import com.wmc.messagecenter.mvp.model.entity.SingleEntity;
import com.wmc.messagecenter.mvp.presenter.UCPresenter;

import io.reactivex.functions.Consumer;

public class MessageActivity extends BaseMVPActivity<UCPresenter> implements UserCenter.UserCenterView {
    @Override
    protected void bindView() {

    }

    @Override
    protected void createPresenter() {
        mPresenter= new UCPresenter(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        mPresenter.getSingleMessage(new SingleEntity()).subscribe(new Consumer<Entity<SingleEntity>>() {
            @Override
            public void accept(Entity<SingleEntity> singleEntityEntity) throws Exception {

            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }
}