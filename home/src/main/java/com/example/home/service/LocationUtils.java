package com.example.home.service;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class LocationUtils {
    private LocationManager locationManager;
    static LocationUtils locationUtils;
    public static  LocationUtils getInstance(){
        if (locationUtils == null){
            locationUtils = new LocationUtils();
        }
        return locationUtils;
    }

    /**
     *  gps 提供器
     * @param locationManager
     * @return
     */
    public static String getGPSProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        // gps定位
        if(prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        }
        return null;
    }


    public String getLocations(final Context context){
        String strLocation = "0,0";
        DecimalFormat df = new DecimalFormat("#####0.0000");
        if (!checkPermission(context)){
            Log.d("===","定位权限关闭，无法获取地理位置");
            Toast.makeText(context,"定位权限关闭，无法获取地理位置",Toast.LENGTH_SHORT).show();
        }
        try {
            Log.d("===","定位权限开启，可以获取地理位置");
            //获取系统的服务，
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            //创建一个criteria对象
            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            //设置不需要获取海拔方向数据
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            //设置允许产生资费
            criteria.setCostAllowed(true);
            //要求低耗电
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            String provider = locationManager.getBestProvider(criteria, true);
            Log.i("Tobin", "Location Provider is "+ provider);
            Location location = locationManager.getLastKnownLocation(provider);

            /**
             * 重要函数，监听数据测试
             * 位置提供器、监听位置变化的时间间隔（毫秒），监听位置变化的距离间隔（米），LocationListener监听器
             */
           locationManager.requestLocationUpdates(provider, 100000, 50, new LocationListener() {
               @Override
               public void onLocationChanged(@NonNull Location location) {
                    Log.d("===",location.toString());
               }
               @Override
               public void onProviderEnabled(String provider) {
                   Log.d("===","GPS开启了!");
               }
               //GPS关闭的时候调用
               @Override
               public void onProviderDisabled(String provider) {
                   Log.d("===","GPS关闭了!");
               }
            @Override
               public void onStatusChanged(String provider, int status, Bundle extras) {
                   switch (status){
                       case LocationProvider.AVAILABLE:
                           Log.d("===","当前GPS为可用状态!");
                           break;

                       case LocationProvider.OUT_OF_SERVICE:
                           Log.d("===","当前GPS不在服务内!");
                           break;

                       case LocationProvider.TEMPORARILY_UNAVAILABLE:
                           Log.d("===","当前GPS为暂停服务状态!");
                           break;
                       default:
               }
           }});


            //第一次获得设备的位置
//            if (location != null){
//                strLocation = df.format(location.getLatitude()) + "," + df.format(location.getLongitude());
//                // 耗时操作
////                strLocation += " " + convertAddress(context, location.getLatitude(),location.getLongitude());

//            }


        }catch (SecurityException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        return strLocation;

    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */
    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.d("===",location+"你好");
        }
        @Override
        public void onProviderDisabled(String provider){
            Log.i("Tobin", "Provider now is disabled..");
        }
        @Override
        public void onProviderEnabled(String provider){
            Log.i("Tobin", "Provider now is enabled..");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("===",provider+"你好");

        }
    };


//    /**
//     * @param latitude 经度
//     * @param longitude 纬度
//     * @return 详细位置信息 GeoCoder是基于后台backend的服务，因此这个方法不是对每台设备都适用。
//     */
//    public String convertAddress(Context context, double latitude, double longitude) {
//        Geocoder mGeocoder = new Geocoder(context, Locale.getDefault());
//        StringBuilder mStringBuilder = new StringBuilder();
//
//        try {
//            List<Address> mAddresses = mGeocoder.getFromLocation(latitude, longitude, 1);
//            if (!mAddresses.isEmpty()) {
//                Address address = mAddresses.get(0);
//                mStringBuilder.append(address.getCountryName()).append(", ").append(address.getAdminArea()).append(", ").append(address.getLocality());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return mStringBuilder.toString();
//    }

    private boolean checkPermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION");
        return perm == PackageManager.PERMISSION_GRANTED;
    }
}
