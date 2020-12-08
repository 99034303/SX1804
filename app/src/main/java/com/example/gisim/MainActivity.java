package com.example.gisim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载主题
        setTheme(R.style.AppTheme_);
        setContentView(R.layout.activity_main);

    }
}