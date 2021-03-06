package com.groupone.common;

import android.app.Application;

public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    protected abstract void init();
}
