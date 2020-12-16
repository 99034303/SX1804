package com.example.gisim;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.view.BottomTabLayout;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.view.BottomTabItemView;
import com.example.view.BottomTabLayout;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private BottomTabLayout test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载主题
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);

    }
}
