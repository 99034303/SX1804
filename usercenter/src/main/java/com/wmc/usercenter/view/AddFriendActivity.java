package com.wmc.usercenter.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.wmc.usercenter.R;
import com.wmc.usercenter.fragment.Search_Flock_Fragment;
import com.wmc.usercenter.fragment.Search_People_Fragment;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity {
    private ArrayList<Fragment> tabList = new ArrayList<>();
    private SlidingTabLayout adduserTvSlid;
    private ViewPager useraddVp;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        initView();
        tabList.add(new Search_People_Fragment());
        tabList.add(new Search_Flock_Fragment());
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adduserTvSlid.setViewPager(useraddVp,new String[]{"找人","找群"},this,tabList);
    }

    private void initView() {
        adduserTvSlid = (SlidingTabLayout) findViewById(R.id.adduser_tv_slid);
        useraddVp = (ViewPager) findViewById(R.id.useradd_vp);
    }

}