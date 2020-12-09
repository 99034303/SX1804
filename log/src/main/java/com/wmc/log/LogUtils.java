package com.wmc.log;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.wmc.myinterface.Log;

import java.util.Iterator;
import java.util.ServiceLoader;

public class LogUtils {
    private String tag="===";
    private boolean isLog=true;

    private static volatile LogUtils instance;

    private Log next;

    private LogUtils(Log next, Context context) {
        this.next=next;
        isDebug(context);
    }

    public static LogUtils getInstance(Context context) {
        if (instance==null){
            synchronized (LogUtils.class){
                if (instance==null){
                    ServiceLoader<Log> load = ServiceLoader.load(Log.class);
                    Iterator<Log> iterator = load.iterator();
                    Log next = iterator.next();
                    return new LogUtils(next,context);
                }
            }
        }
        return instance;
    }

    public void setTag(String tag){
        this.tag=tag;
    }

    public void v(String msg){
        next.logV(tag,msg);
    }

    public void d(String msg){
        if (isLog) {
            next.logD(tag,msg);
        }
    }

    public void i(String msg){
        if (isLog) {
            next.logI(tag,msg);
        }
    }

    public void w(String msg){
        if (isLog) {
            next.logW(tag,msg);
        }
    }

    public void e(String msg){
        if (isLog) {
            next.logE(tag,msg);
        }
    }

    public void v(String tag,String msg){
        if (isLog) {
            next.logV(tag,msg);
        }
    }
    public void d(String tag,String msg){
        if (isLog) {
            next.logD(tag,msg);
        }
    }
    public void i(String tag,String msg){
        if (isLog) {
            next.logI(tag,msg);
        }
    }
    public void w(String tag,String msg){
        if (isLog) {
            next.logW(tag,msg);
        }
    }
    public void e(String tag,String msg){
        if (isLog) {
            next.logE(tag,msg);
        }
    }

    public void systemOut(String msg){
            next.systemOut(msg);
    }

    private void isDebug(Context context){
        ApplicationInfo info = context.getApplicationInfo();
        if (info.flags==ApplicationInfo.FLAG_DEBUGGABLE){
            isLog=true;
        }else {
            isLog=false;
        }
    }

}
