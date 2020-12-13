package com.example.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.maps.MapView;
import com.example.home.adapter.ActiveListAdapter;
import com.example.home.view.ActiveListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ActiveListView viewHomeMainActiveList;
    private List<String> titles=new ArrayList<>();
    private ActiveListAdapter activeListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initTitles();
        viewHomeMainActiveList = (ActiveListView) findViewById(R.id.view_home_main_activeList);
        activeListAdapter=new ActiveListAdapter(R.layout.adapter_home_main_active_list,titles);
        viewHomeMainActiveList.setAdapter(activeListAdapter);
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
