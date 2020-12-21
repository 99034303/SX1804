package com.example.gaode_map;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CoordinateConverter;
import com.bw.xmpplibrary.XmppManager;

public class MyService extends JobIntentService {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            Log.d("===","监听回调");

            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
//可在其中解析amapLocation获取相应内容。
                    Log.d("===","成功获取到位置");
                    Log.d("===",amapLocation.toString()+"12345");
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        Looper.prepare();
        Log.d("===","Service启动");

        String network = GetGPSUtil.getInstance().getLngAndLatWithNetwork(this);

        Log.d("===",network);
////初始化定位
//        mLocationClient = new AMapLocationClient(getApplicationContext());
////设置定位回调监听
//        mLocationClient.setLocationListener(mLocationListener);
////初始化AMapLocationClientOption对象
//        mLocationOption = new AMapLocationClientOption();
//        AMapLocationClientOption option = new AMapLocationClientOption();
//        /**
//         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
//         */
//        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//        if(null != mLocationClient){
//            mLocationClient.setLocationOption(option);
//            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//            mLocationClient.stopLocation();
//            mLocationClient.startLocation();
//        }
//        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //获取一次定位结果：
////该方法默认为false。
//        mLocationOption.setOnceLocation(true);
//
////获取最近3s    内精度最高的一次定位结果：
////设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);
//
////设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//        mLocationOption.setInterval(1000);
//        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);
//        //设置是否允许模拟位置,默认为true，允许模拟位置
//        mLocationOption.setMockEnable(true);
//        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
//        mLocationOption.setHttpTimeOut(20000);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
////启动定位
//        mLocationClient.startLocation();
        Looper.loop();
    }

    @Override
    public boolean onStopCurrentWork() {
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        Log.d("===","Service暂停");
        return super.onStopCurrentWork();
    }

    @Override
    public void onDestroy() {
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        Log.d("===","Service销毁");
        super.onDestroy();
    }

}
