package com.wmc.usercenter.entity;

public class AddEntity {

    /**
     * addtype : 0
     * alias :
     * friendid : 0
     * localgroupid : 0
     * userid : 0
     */

    private int addtype;
    private String alias;
    private int friendid;
    private int localgroupid;
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

    public int getFriendid() {
        return friendid;
    }

    public void setFriendid(int friendid) {
        this.friendid = friendid;
    }

    public int getLocalgroupid() {
        return localgroupid;
    }

    public void setLocalgroupid(int localgroupid) {
        this.localgroupid = localgroupid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public AddEntity(int addtype, String alias, int friendid, int localgroupid, int userid) {
        this.addtype = addtype;
        this.alias = alias;
        this.friendid = friendid;
        this.localgroupid = localgroupid;
        this.userid = userid;
    }
}
