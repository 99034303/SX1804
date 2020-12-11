package com.example.gisim;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.view.BottomTabLayout;

public class MainActivity extends AppCompatActivity {
    private BottomTabLayout test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载主题
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        test = (BottomTabLayout) findViewById(R.id.test);
        test.addTab(1,R.mipmap.hd, 1);
        test.addTab(2,R.mipmap.ic_launcher, 2);
        test.addTab(3,R.mipmap.ic_launcher_round, 3);
        test.addTab(4,R.mipmap.hd, 4);
        test.addTab(5,R.mipmap.ic_launcher, 5);
    }
}