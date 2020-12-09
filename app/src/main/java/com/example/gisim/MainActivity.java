package com.example.gisim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.view.BottomTabItemView;
import com.example.view.BottomTabLayout;
import com.wmc.imageloader.ImageUtils;

public class MainActivity extends AppCompatActivity {
    private BottomTabLayout test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        test = (BottomTabLayout) findViewById(R.id.test);
        test.addTab(R.mipmap.hd, 8);
        test.addTab(R.mipmap.ic_launcher, 8);
        test.addTab(R.mipmap.ic_launcher_round, 8);
        test.addTab(R.mipmap.hd, 8);
        test.addTab(R.mipmap.ic_launcher, 8);
    }
}