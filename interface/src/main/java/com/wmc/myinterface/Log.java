package com.wmc.myinterface;

/**
 * @author 魏铭池
 */
public interface Log {
    void logV(String tag,String msg);
    void logD(String tag,String msg);
    void logI(String tag,String msg);
    void logW(String tag,String msg);
    void logE(String tag,String msg);
    void systemOut(String msg);
}
