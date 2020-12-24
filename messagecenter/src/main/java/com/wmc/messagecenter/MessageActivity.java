package com.wmc.messagecenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.xmpplibrary.XmppManager;
import com.example.mvp.view.BaseMVPActivity;
import com.wmc.messagecenter.mvp.contract.UserCenter;
import com.wmc.messagecenter.mvp.model.entity.Entity;
import com.wmc.messagecenter.mvp.model.entity.SingleEntity;
import com.wmc.messagecenter.mvp.presenter.UCPresenter;

import org.jivesoftware.smack.chat2.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class MessageActivity extends BaseMVPActivity<UCPresenter> implements UserCenter.UserCenterView {

    private Object chat;
    SingleEntity singleEntity;
    @Override
    protected void bindView() {

    }

    @Override
    protected void createPresenter() {
        mPresenter= new UCPresenter(this);
        chat = XmppManager.getInstance().getXmppMsgManager().getFriendChat("");
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {

        mPresenter.sendSingleMessage((Chat) chat,"",1,1,1).subscribe(new Consumer<Entity<Boolean>>() {
            @Override
            public void accept(Entity<Boolean> singleEntityEntity) throws Exception {
                if (singleEntityEntity.getCode()==0) {

                }else {
                    Toast.makeText(MessageActivity.this, singleEntityEntity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        mPresenter.getSingleMessage().subscribe(new Consumer<Entity<Boolean>>() {
            @Override
            public void accept(Entity<Boolean> singleEntityEntity) throws Exception {
                if (singleEntityEntity.getCode()==0) {

                }else {
                    Toast.makeText(MessageActivity.this, singleEntityEntity.getMsg(), Toast.LENGTH_SHORT).show();
                }
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