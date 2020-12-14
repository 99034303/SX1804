package com.wmc.usercenter;

import android.app.Application;
import com.wmc.sp.SPUtils;
import com.wmc.sp.SQLiteManager;


public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化友盟
//        initUMeng();

        //初始化SP
        SPUtils.getInstance("gisim",this);
        //初始化数据库
        SQLiteManager.getInstance(this);
    }


    //初始化友盟
//    private void initUMeng() {
//        // 在此处调用基础组件包提供的初始化函数 相应信息可在应用管理 -> 应用信息 中找到 http://message.umeng.com/list/apps
//        // 参数一：当前上下文context；
//        // 参数二：应用申请的Appkey（需替换）；
//        // 参数三：渠道名称；
//        // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
//        // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）
//        UMConfigure.init(this, "5fcef2465ea7c714dc9fb01f", "Umeng",
//                UMConfigure.DEVICE_TYPE_PHONE, "e9a00f828e95c390c3148626c417e70a");
//
//
//        UMConfigure.init(this, "59892f08310c9307b60023d0", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
//        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
//
//
//        //获取消息推送代理示例
//        PushAgent mPushAgent = PushAgent.getInstance(this);
//        //注册推送服务，每次调用register方法都会回调该接口
//        mPushAgent.register(new IUmengRegisterCallback() {
//
//            @Override
//            public void onSuccess(String deviceToken) {
//                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
//                Log.i("zzz","注册成功：deviceToken：-------->  " + deviceToken);
//            }
//
//            @Override
//            public void onFailure(String s, String s1) {
//                Log.e("zzz","注册失败：-------->  " + "s:" + s + ",s1:" + s1);
//            }
//        });
//    }
}
