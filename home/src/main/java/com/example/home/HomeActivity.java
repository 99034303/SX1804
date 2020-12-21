package com.example.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.gaode_map.BaseMapActivity;
import com.example.home.adapter.ActiveListAdapter;
import com.example.home.adapter.FriendAdapter;
import com.example.home.adapter.Friend_GroupAdapter;
import com.example.home.view.ActiveListView;
import com.example.view.BottomTabLayout;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * @author Administrator
 */
@Route(path = "/home/HomeActivity")
public class HomeActivity extends BaseMapActivity {
    private ActiveListView viewHomeMainActiveList;
    private List<String> titles=new ArrayList<>();
    private ActiveListAdapter activeListAdapter;
    private BottomTabLayout bottomLayout;
    private RecyclerView friendGroupRv;
    private RecyclerView friendRv;
    private FriendAdapter friendAdapter;
    private Friend_GroupAdapter friend_groupAdapter;
    private List<Integer> friend_list = new ArrayList();
    private List<Integer> friend_group_list = new ArrayList();
    @Override
    protected void bindView() {

        //好友分组
        friendGroupRv = (RecyclerView) findViewById(R.id.friend_group_rv);
        friendGroupRv.setLayoutManager(new LinearLayoutManager(this));
        friend_groupAdapter  = new Friend_GroupAdapter(R.layout.friend_item1,friend_group_list);
        friendGroupRv.setAdapter(friend_groupAdapter);
        friend_groupAdapter.notifyDataSetChanged();
        //好友
        friendRv = (RecyclerView) findViewById(R.id.friend_rv);
        friendRv.setLayoutManager(new LinearLayoutManager(this));
        friendAdapter  = new FriendAdapter(R.layout.friend_item1,friend_list);
        friendRv.setAdapter(friendAdapter);
        friendAdapter.notifyDataSetChanged();

        ARouter.getInstance().inject(this);
        bottomLayout = (BottomTabLayout) findViewById(R.id.bottom_layout);
        viewHomeMainActiveList = (ActiveListView) findViewById(R.id.view_home_main_activeList);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initData() {
        //添加底部标题
        bottomLayout.addTab(0,R.mipmap.person,0);
        bottomLayout.addTab(1,R.mipmap.active,0);
        bottomLayout.addTab(2,R.mipmap.message,0);
        bottomLayout.addTab(2,R.mipmap.camera,0);
        bottomLayout.addTab(3,R.mipmap.friends_space,0);
        //添加好友头像
        friend_list.add(R.drawable.ic_friend);
        friend_list.add(R.drawable.ic_friend);
        friend_list.add(R.drawable.ic_friend);
        friend_list.add(R.drawable.ic_friend);
        friend_group_list.add(R.drawable.ic_friend_group);
        friend_group_list.add(R.drawable.ic_friend_group);
        friend_group_list.add(R.drawable.ic_friend_group);
        friend_group_list.add(R.drawable.ic_friend_group);
        friend_group_list.add(R.drawable.ic_friend_group);
        //刷新适配器
        friendAdapter.notifyDataSetChanged();
        friend_groupAdapter.notifyDataSetChanged();

        bottomLayout.setOnItemClickListener(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer id) {
                switch (id){
                    case 0:
                        ARouter.getInstance().build("/userCenter/ContactsActivity").navigation();
                        break;
                }
                return null;
            }
        });

    }

    @Override
    protected void initView() {
        activeListAdapter = new ActiveListAdapter(R.layout.adapter_home_main_active_list, titles);
        viewHomeMainActiveList.setAdapter(activeListAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected int getViewId() {
        return R.id.map;
    }
}