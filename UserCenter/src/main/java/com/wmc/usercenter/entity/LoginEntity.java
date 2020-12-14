package com.wmc.usercenter.entity;

/**
 * 登录成功返回的实体类
 */
public class LoginEntity {
    /**
     * id : 22
     * phonenumber : 123
     * nick : null
     * headimg : null
     * imaccount : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjMifQ.xKPoMpjMrGHF2iDgqhXrvyypo8HGEZtqDcnND2tQyPo
     * lon : null
     * lat : null
     */

    private int id;
    private String phonenumber;
    private String nick;
    private String headimg;
    private String imaccount;
    private String token;
    private String lon;
    private String lat;

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getImaccount() {
        return imaccount;
    }

    public void setImaccount(String imaccount) {
        this.imaccount = imaccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "LoginEntity{" +
                "id=" + id +
                ", phonenumber='" + phonenumber + '\'' +
                ", nick='" + nick + '\'' +
                ", headimg='" + headimg + '\'' +
                ", imaccount='" + imaccount + '\'' +
                ", token='" + token + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
