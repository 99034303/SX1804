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
        test = (BottomTabLayout) findViewById(R.id.test);
        test.addTab(1,R.mipmap.ic_launcher,12);
        test.addTab(2,R.mipmap.ic_launcher_round, 0);
        test.addTab(3,R.mipmap.ic_launcher_round, 30);
        test.addTab(4,R.mipmap.ic_launcher, 0);
        test.addTab(5,R.mipmap.ic_launcher, 78);
        test.setOnItemClickListener(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer id) {
                Toast.makeText(MainActivity.this, "id:"+id, Toast.LENGTH_SHORT).show();
                return null;
            }
        });



    }
}
