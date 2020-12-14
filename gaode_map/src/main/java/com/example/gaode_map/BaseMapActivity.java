package com.example.gaode_map;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CustomMapStyleOptions;
import com.example.mvp.view.BaseMVPActivity;

public abstract class BaseMapActivity extends BaseMVPActivity {
   protected MapView mMapView = null;
    protected AMap aMap;
    protected CustomMapStyleOptions customMapStyleOptions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    "android.permission.WRITE_EXTERNAL_STORAGE",
                    "android.permission.ACCESS_NETWORK_STATE",
                    "android.permission.ACCESS_WIFI_STATE",
                    "android.permission.READ_PHONE_STATE",
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.ACCESS_FINE_LOCATION"

            }, 100);
        }
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
