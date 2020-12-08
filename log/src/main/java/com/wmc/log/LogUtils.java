package com.wmc.log;

import com.wmc.myinterface.Log;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Administrator
 */
public class LogUtils {
    static ServiceLoader<Log> load = ServiceLoader.load(Log.class);
    static Iterator<Log> iterator = load.iterator();
    static Log next = iterator.next();
    public static String tag="wmc";
    public static boolean isLog=true;
    public static void v(String msg){
        next.logV(tag,msg);
    }

    public static void d(String msg){
        if (isLog) {
            next.logD(tag,msg);
        }
    }

    public static void i(String msg){
        if (isLog) {
            next.logI(tag,msg);
        }
    }

    public static void w(String msg){
        if (isLog) {
            next.logW(tag,msg);
        }
    }

    public static void e(String msg){
        if (isLog) {
            next.logE(tag,msg);
        }
    }

    public static void v(String tag,String msg){
        if (isLog) {
            next.logV(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if (isLog) {
            next.logD(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if (isLog) {
            next.logI(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if (isLog) {
            next.logW(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if (isLog) {
            next.logE(tag,msg);
        }
    }

    public static void systemOut(String msg){
            next.systemOut(msg);
    }

}
