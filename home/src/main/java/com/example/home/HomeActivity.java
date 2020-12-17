package com.example.home;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.amap.api.maps.MapView;
import com.example.gaode_map.BaseMapActivity;
import com.example.home.adapter.ActiveListAdapter;
import com.example.home.view.ActiveListView;
import com.example.view.BottomTabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        bottomLayout.addTab(0,R.drawable.person,0);
        bottomLayout.addTab(1,R.mipmap.ic_launcher_round,5);
        bottomLayout.addTab(2,R.mipmap.ic_launcher,2);
        bottomLayout.addTab(2,R.mipmap.ic_launcher,2);
        bottomLayout.addTab(3,R.mipmap.ic_launcher_round,3);
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