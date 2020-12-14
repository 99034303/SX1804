package com.bw.xmpplibrary.callback;


import com.bw.xmpplibrary.entity.MsgEntity;

public interface IMsgCallback {
    void Success(MsgEntity msgEntity);
    void Failed(Throwable throwable);
}
