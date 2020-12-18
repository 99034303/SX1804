package com.example.home;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.gaode_map.BaseMapActivity;
import com.example.home.adapter.ActiveListAdapter;
import com.example.home.view.ActiveListView;
import com.example.view.BottomTabLayout;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Administrator
 */
@Route(path = "/home/HomeActivity")
public class HomeActivity extends BaseMapActivity {
    private ActiveListView viewHomeMainActiveList;
    private List<String> titles=new ArrayList<>();
    private ActiveListAdapter activeListAdapter;
    private BottomTabLayout bottomLayout;

    @Override
    protected void bindView() {
        ARouter.getInstance().inject(this);
        bottomLayout = (BottomTabLayout) findViewById(R.id.bottom_layout);
        viewHomeMainActiveList = (ActiveListView) findViewById(R.id.view_home_main_activeList);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initData() {
        bottomLayout.addTab(0,R.mipmap.person,0);
        bottomLayout.addTab(1,R.mipmap.active,0);
        bottomLayout.addTab(2,R.mipmap.message,0);
        bottomLayout.addTab(2,R.mipmap.camera,0);
        bottomLayout.addTab(3,R.mipmap.friends_space,0);



    }

    @Override
    protected void initView() {
        activeListAdapter=new ActiveListAdapter(R.layout.adapter_home_main_active_list,titles);
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