package com.wmc.usercenter.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mvp.view.BaseMVPFragment;
import com.example.net.BaseEntity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wmc.usercenter.R;
import com.wmc.usercenter.adapter.FriendAdapter;
import com.wmc.usercenter.contract.Contract;
import com.wmc.usercenter.entity.AddEntity;
import com.wmc.usercenter.entity.FriendEntity;
import com.wmc.usercenter.entity.LoginEntity;
import com.wmc.usercenter.presenter.UserCenterPresenter;

import java.util.ArrayList;
import java.util.List;

public class Search_People_Fragment extends BaseMVPFragment<UserCenterPresenter> implements Contract.View {
    private EditText searchpeopleEd;
    private TextView searchpeopleTvAdduser;
    private TextView searchpeopleTvZxing;
    private RecyclerView searchpeopleRec;
    private Button btnSearchPerson;
    private List<FriendEntity> friendEntitylist = new ArrayList<>();
    private FriendAdapter friendAdapter ;
    @Override
    protected void bindView() {
        //搜索
        searchpeopleEd = (EditText) findViewById(R.id.searchpeople_ed);
        btnSearchPerson = (Button) findViewById(R.id.btn_search_person);
        //手机联系人
        searchpeopleTvAdduser = (TextView) findViewById(R.id.searchpeople_tv_adduser);
        //Zxing
        searchpeopleTvZxing = (TextView) findViewById(R.id.searchpeople_tv_zxing);
        //RecyclerView推荐
        searchpeopleRec = (RecyclerView) findViewById(R.id.searchpeople_rec);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new UserCenterPresenter(this);
    }

    @Override
    protected void initData() {
        //添加好友功能
        friendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId()==R.id.friend_add){
                    FriendEntity friendEntity = friendEntitylist.get(position);
                    AddEntity addEntity = new AddEntity(0,"",friendEntity.getId(),0,0);
                    mPresenter.AddFriend(addEntity);
                }
            }
        });

        //实现搜索好友功能
        searchpeopleEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearchPerson.setVisibility(View.VISIBLE);
            }
        });
        btnSearchPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSearchPerson.setVisibility(View.GONE);
                String userName = searchpeopleEd.getText().toString();
                if (userName.equals("")){
                    Toast.makeText(getActivity(), "请输入好友用户名或电话号码", Toast.LENGTH_SHORT).show();
                }else {
                    //搜索好友
                    mPresenter.getFriend(userName);
                }
                searchpeopleEd.setText("");
            }
        });
        //添加手机联系人
        searchpeopleTvAdduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //此处填入好友页面activity
//                Intent intent = new Intent(getActivity(),);
//                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        searchpeopleRec.setLayoutManager(new LinearLayoutManager(getContext()));
        friendAdapter = new FriendAdapter(R.layout.friend_item,friendEntitylist);
        searchpeopleRec.setAdapter(friendAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search__people;
    }

    @Override
    public void updateLoginUI(BaseEntity<LoginEntity> baseEntity) {

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

    //搜索好友结果
    @Override
    public void getFriend(List<FriendEntity> friendEntity) {
        //清空之前搜索的好友
        friendEntitylist.clear();
        friendEntitylist .addAll(friendEntity);
        //刷新适配器，显示搜索到的好友信息
        friendAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateRequestAddFriendUI(BaseEntity<List<FriendEntity>> result) {
        
    }

    @Override
    public void AddFriend(BaseEntity<Boolean> baseEntity) {
        Boolean data = baseEntity.getData();
        if (data){
            Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();
        }
    }
}