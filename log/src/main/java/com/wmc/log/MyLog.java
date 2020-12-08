package com.wmc.log;

import com.wmc.myinterface.Log;

public class MyLog implements Log {
    @Override
    public void logV(String tag, String msg) {
        android.util.Log.v(tag, msg);
    }

    @Override
    public void logD(String tag, String msg) {
        android.util.Log.d(tag, msg);
    }

    @Override
    public void logI(String tag, String msg) {
        android.util.Log.i(tag, msg);
    }

    @Override
    public void logW(String tag, String msg) {
        android.util.Log.w(tag, msg);
    }

    @Override
    public void logE(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }

    @Override
    public void systemOut(String msg) {
        System.out.println(msg);
    }
}
