package com.wmc.messagecenter.mvp.model.entity;

public class SingleEntity {
    private String blltime;
    private String content;
    private int fromuser;
    private int isread;
    private int msgtype2;
    private int msgtypeid;
    private int touser;

    public SingleEntity(String content) {
        this.content = content;
    }

    public SingleEntity() {
    }

    public String getBlltime() {
        return blltime;
    }

    public void setBlltime(String blltime) {
        this.blltime = blltime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFromuser() {
        return fromuser;
    }

    public void setFromuser(int fromuser) {
        this.fromuser = fromuser;
    }

    public int getIsread() {
        return isread;
    }

    public void setIsread(int isread) {
        this.isread = isread;
    }

    public int getMsgtype2() {
        return msgtype2;
    }

    public void setMsgtype2(int msgtype2) {
        this.msgtype2 = msgtype2;
    }

    public int getMsgtypeid() {
        return msgtypeid;
    }

    public void setMsgtypeid(int msgtypeid) {
        this.msgtypeid = msgtypeid;
    }

    public int getTouser() {
        return touser;
    }

    public void setTouser(int touser) {
        this.touser = touser;
    }
}
