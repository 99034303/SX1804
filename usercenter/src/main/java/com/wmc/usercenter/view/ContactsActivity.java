package com.wmc.usercenter.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvp.view.BaseMVPActivity;
import com.example.net.BaseEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.wmc.usercenter.R;
import com.wmc.usercenter.adapter.RequestAddFriendListAdapter;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.entity.RequestAddFriendsResponseEntity;
import com.wmc.usercenter.entity.TabEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/userCenter/ContactsActivity")
public class ContactsActivity extends BaseMVPActivity<UserCenterPresenter> implements Contract.View {
    private ImageView imgUserCenterContactsBack;
    private ImageView imgUserCenterContactsToAddFriend;
    private ViewStub vsUserCenterContactsFriendReuquest;
    private CommonTabLayout tlUserCenterContactsType;
    private ArrayList<CustomTabEntity> tabs=new ArrayList<>();
    private List<RequestAddFriendsResponseEntity> requestaddFriendData=new ArrayList<>();
    private RequestAddFriendListAdapter requestAddFriendListAdapter;
    private RecyclerView requestAddFriendList;

    @Override
    protected void bindView() {
        imgUserCenterContactsBack = (ImageView) findViewById(R.id.img_userCenter_contacts_back);
        imgUserCenterContactsToAddFriend = (ImageView) findViewById(R.id.img_userCenter_contacts_toAddFriend);
        vsUserCenterContactsFriendReuquest = (ViewStub) findViewById(R.id.vs_userCenter_contacts_friendReuquest);
        tlUserCenterContactsType = (CommonTabLayout) findViewById(R.id.tl_userCenter_contacts_type);
    }

    @Override
    protected void createPresenter() {
        this.mPresenter=new UserCenterPresenter(this);
    }

    @Override
    protected void initData() {
        initTabs();
    }

    @Override
    protected void initView() {

    }

    //初始化标题栏
    private void initTabs(){
        tabs.add(new TabEntity("好友"));
        tabs.add(new TabEntity("分组"));
        tabs.add(new TabEntity("群聊"));
        tlUserCenterContactsType.setTabData(tabs);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void updateLoginUI(BaseEntity<LoginEntity> baseEntity) {

    }

    @Override
    public void updateRegisterUI(BaseEntity<Boolean> baseEntity) {

    }

    /**
     * 刷新请求添加好友列表
     */
    @Override
    public void updateRequestAddFriendUI(BaseEntity<List<RequestAddFriendsResponseEntity>> result) {
        List<RequestAddFriendsResponseEntity> data = result.getData();
        //判断是否有请求数据
        if(data.size()>0){
            //显示viewStub
            vsUserCenterContactsFriendReuquest.inflate();
            //绑定控件
            requestAddFriendList=findViewById(R.id.list_userCenter_contacts_friendRequestList);
            requestAddFriendList.setLayoutManager(new LinearLayoutManager(this));
            //设置数据
            requestaddFriendData.addAll(data);
            requestAddFriendListAdapter=new RequestAddFriendListAdapter(R.layout.adapter_usercenter_contacts_request_add_friend_list,requestaddFriendData);
            requestAddFriendList.setAdapter(requestAddFriendListAdapter);
        }
    }
}