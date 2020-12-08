package com.example.gisim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.view.BottomTabItemView;
import com.example.view.BottomTabLayout;

public class MainActivity extends AppCompatActivity {
    private BottomTabLayout test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (BottomTabLayout) findViewById(R.id.test);
        test.addTab(R.mipmap.hd,8);
        test.addTab(R.mipmap.hd,8);
        test.addTab(R.mipmap.hd,8);
        test.addTab(R.mipmap.hd,8);
        test.addTab(R.mipmap.hd,8);
    }
}