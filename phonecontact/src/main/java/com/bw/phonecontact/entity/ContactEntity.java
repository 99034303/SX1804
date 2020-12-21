package com.bw.phonecontact.entity;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * 手机联系人实体类
 */
public class ContactEntity implements Comparable<ContactEntity> {
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
    public int compareTo(ContactEntity o) {
        try {
            String shortPinyin1 = PinyinHelper.getShortPinyin(o.getName().substring(0, 1));
            String shortPinyin2 = PinyinHelper.getShortPinyin(this.getName().substring(0, 1));
            char c = shortPinyin1.charAt(0);
            char c2 = shortPinyin2.charAt(0);
            if (c > c2){
                return -1;
            }else {
                return 1;
            }
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
