package com.example.gaode_map;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.example.mvp.view.BaseMVPActivity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public abstract class BaseMapActivity extends BaseMVPActivity {
   protected MapView mMapView = null;
    protected AMap aMap;
    protected CustomMapStyleOptions customMapStyleOptions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取地图控件引用
        mMapView = findViewById(getViewId());
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //添加自定义地图的显示样式
        if (customMapStyleOptions==null){
            customMapStyleOptions = new CustomMapStyleOptions()
                    .setEnable(true)
                    //利用网络地图的样式id加载自定义样式
                    .setStyleId("81db29be347e356f6cdc6eace17acede");
//                    //根据本地的离线文件加载自定义样式
//                    .setStyleDataPath("/mnt/sdcard/style.data")
//                    .setStyleExtraPath("/mnt/sdcard/style_extra.data");
        }
        aMap.setCustomMapStyle(customMapStyleOptions);

        if (!isIgnoringBatteryOptimizations()){
            requestIgnoreBatteryOptimizations();
        }else {
            MyService.enqueueWork(this,MyService.class,8,new Intent());
        }

    }


    private boolean isIgnoringBatteryOptimizations() {
        boolean isIgnoring = false;

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(getPackageName());
        }
        return isIgnoring;
    }


    public void requestIgnoreBatteryOptimizations() {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent,103);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==103){
            if (!isIgnoringBatteryOptimizations()){
                Toast.makeText(this, "请允许软件后台运行", Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected abstract int getViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        if (mMapView!=null){
            mMapView.onDestroy();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        if (mMapView!=null){
            mMapView.onResume();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        if (mMapView!=null){
            mMapView.onPause();

        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (mMapView!=null){
            mMapView.onSaveInstanceState(outState);

        }
    }
}
