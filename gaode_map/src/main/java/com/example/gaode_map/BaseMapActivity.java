package com.example.gaode_map;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.example.mvp.view.BaseMVPActivity;

public abstract class BaseMapActivity extends BaseMVPActivity {
   protected MapView mMapView = null;
    protected AMap aMap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMapView = findViewById(getViewId());
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
            Log.d("wt","1222222");
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
