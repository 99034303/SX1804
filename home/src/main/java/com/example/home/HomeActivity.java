package com.example.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.gaode_map.BaseMapActivity;
import com.example.home.adapter.ActiveListAdapter;
import com.example.home.view.ActiveListView;
import com.example.view.BottomTabLayout;
import com.groupone.common.utils.ZipUitls;

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

    @Override
    protected void bindView() {
        bottomLayout = (BottomTabLayout) findViewById(R.id.bottom_layout);
        viewHomeMainActiveList = (ActiveListView) findViewById(R.id.view_home_main_activeList);
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initData() {
        ZipUitls.executeZip("/storage/emulated/0/data/token.txt","/storage/emulated/0/data/myToken.zip");
        //添加底部标题
        bottomLayout.addTab(0,R.mipmap.person,0);
        bottomLayout.addTab(1,R.mipmap.active,0);
        bottomLayout.addTab(2,R.mipmap.message,0);
        bottomLayout.addTab(2,R.mipmap.camera,0);
        bottomLayout.addTab(3,R.mipmap.friends_space,0);

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
        ARouter.getInstance().inject(this);
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