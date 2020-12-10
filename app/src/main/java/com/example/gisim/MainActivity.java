package com.example.gisim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.view.BottomTabLayout;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.view.BottomTabItemView;
import com.example.view.BottomTabLayout;
import com.wmc.imageloader.ImageUtils;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private BottomTabLayout test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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