package com.wmc.usercenter.entity;

<<<<<<< HEAD
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
=======
public class FriendEntity {
    /**
     * addtype : 0
     * alias :
     * ctime :
     * friendid : 0
     * headimg :
     * id : 0
     * imaccount :
     * lat :
     * localgroupid : 0
     * lon :
     * nick :
     * phonenumber :
     * token :
     * userid : 0
     */

    private int addtype;
    private String alias;
    private String ctime;
    private int friendid;
    private String headimg;
    private int id;
    private String imaccount;
    private String lat;
    private int localgroupid;
    private String lon;
    private String nick;
    private String phonenumber;
    private String token;
    private int userid;

    public int getAddtype() {
        return addtype;
    }

    public void setAddtype(int addtype) {
        this.addtype = addtype;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImaccount() {
        return imaccount;
    }

    public void setImaccount(String imaccount) {
        this.imaccount = imaccount;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getLocalgroupid() {
        return localgroupid;
    }

    public void setLocalgroupid(int localgroupid) {
        this.localgroupid = localgroupid;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
>>>>>>> 0bac76145c6ce72afad76f76dc8d6fffab2950af
    }
}
