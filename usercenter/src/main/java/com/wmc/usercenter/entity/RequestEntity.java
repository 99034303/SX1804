package com.wmc.usercenter.entity;


/**
 * 注册、登录的请求实体类
 */
public class RequestEntity {

    //IM系统关联账户 登录接口——无需传递该字段
    private String imaccount;

    //电话号码
    private String phonenumber;

    //登录密码
    private String pwd;



    public String getImaccount() {
        return imaccount;
    }

    public void setImaccount(String imaccount) {
        this.imaccount = imaccount;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "phonenumber='" + phonenumber + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public RequestEntity(String phonenumber, String pwd) {
        this.phonenumber = phonenumber;
        this.pwd = pwd;
    }
}
