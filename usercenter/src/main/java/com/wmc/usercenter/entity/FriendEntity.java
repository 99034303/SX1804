package com.wmc.usercenter.entity;

import java.util.List;

public class FriendEntity {
    /**
     * id : 22
     * phonenumber : 123
     * nick : null
     * headimg : null
     * imaccount : null
     * token : null
     * lon : null
     * lat : null
     */

    private int id;
    private String phonenumber;
    private Object nick;
    private Object headimg;
    private Object imaccount;
    private Object token;
    private Object lon;
    private Object lat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Object getNick() {
        return nick;
    }

    public void setNick(Object nick) {
        this.nick = nick;
    }

    public Object getHeadimg() {
        return headimg;
    }

    public void setHeadimg(Object headimg) {
        this.headimg = headimg;
    }

    public Object getImaccount() {
        return imaccount;
    }

    public void setImaccount(Object imaccount) {
        this.imaccount = imaccount;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getLon() {
        return lon;
    }

    public void setLon(Object lon) {
        this.lon = lon;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "FriendEntity{" +
                "id=" + id +
                ", phonenumber='" + phonenumber + '\'' +
                ", nick=" + nick +
                ", headimg=" + headimg +
                ", imaccount=" + imaccount +
                ", token=" + token +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
