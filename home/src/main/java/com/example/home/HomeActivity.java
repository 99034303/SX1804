package com.example.home;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
=======
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.maps.MapView;
import com.example.gaode_map.BaseMapActivity;
import com.example.home.adapter.ActiveListAdapter;
import com.example.home.view.ActiveListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseMapActivity {
    private ActiveListView viewHomeMainActiveList;
    private List<String> titles=new ArrayList<>();
    private ActiveListAdapter activeListAdapter;

    private RecyclerView listHomeMainActiveList;

    @Override
    protected void bindView() {

    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        initTitles();
        viewHomeMainActiveList = (ActiveListView) findViewById(R.id.view_home_main_activeList);
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

    private void initTitles() {
        titles.add("");
        titles.add("");
        titles.add("");
        titles.add("");
        titles.add("");
        titles.add("");
    }
}
>>>>>>> ffd7ac4089265451389e487f27a141178e2f0f4e
