package com.wmc.messagecenter;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mvp.view.BaseMVPActivity;
import com.wmc.messagecenter.adapter.MessageFriendAdapter;
import com.wmc.messagecenter.mvp.contract.UserCenter;
import com.wmc.messagecenter.mvp.model.entity.Entity;
import com.wmc.messagecenter.mvp.model.entity.SingleEntity;
import com.wmc.messagecenter.mvp.presenter.UCPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MessageActivity extends BaseMVPActivity<UCPresenter> implements UserCenter.UserCenterView {
    private ImageView messageBack;
    private ImageView ivGotoAddFriend;
    private RecyclerView messageRecycler;
    private MessageFriendAdapter adapter;
    private List<String> datas = new ArrayList<>();


    @Override
    protected void bindView() {
        messageBack = (ImageView) findViewById(R.id.message_back);
        ivGotoAddFriend = (ImageView) findViewById(R.id.iv_goto_add_friend);
        messageRecycler = (RecyclerView) findViewById(R.id.message_recycler);

    }

    @Override
    protected void createPresenter() {
        mPresenter= new UCPresenter(this);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            datas.add("好友"+i);
        }

        adapter = new MessageFriendAdapter(R.layout.message_view_item,datas);
        messageRecycler.setAdapter(adapter);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(MessageActivity.this,ChatActivity.class));
            }
        });

//        mPresenter.getSingleMessage(new SingleEntity()).subscribe(new Consumer<Entity<SingleEntity>>() {
//            @Override
//            public void accept(Entity<SingleEntity> singleEntityEntity) throws Exception {
//
//            }
//        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }
}