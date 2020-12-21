package com.wmc.usercenter.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    private String title;

    @Override
    public String getTabTitle() {
        return title;
    }

    public TabEntity(String title) {
        this.title = title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
