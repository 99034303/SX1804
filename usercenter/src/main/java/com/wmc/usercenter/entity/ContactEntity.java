package com.wmc.usercenter.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 手机联系人实体类
 */
public class ContactEntity implements MultiItemEntity {
    private String name;
    private String phone_number;

    @Override
    public String toString() {
        return "ContactEntity{" +
                "name='" + name + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public ContactEntity(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    @Override
    public int getItemType() {
        
        return 0;
    }
}
