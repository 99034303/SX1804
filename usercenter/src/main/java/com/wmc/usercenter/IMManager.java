package com.wmc.usercenter;

import com.bw.xmpplibrary.XmppManager;

public class IMManager {
    private static IMManager imManager;

    public static IMManager getInstance(){
        if (imManager == null){
            imManager = new IMManager();
            return imManager;
        }
        return imManager;
    }

    private XmppManager xmppManager;

    private IMManager(){
        xmppManager = XmppManager.getInstance();
    }


    /**
     * 登录 IM账号
     */
    private void login(String username,String pwd){
        if (!xmppManager.getXmppUserManager().isLogin()){
            xmppManager.getXmppUserManager().login(username,pwd);
        }
    }

    /**
     * (创建)注册 IM账号
     */
    private void register(String username,String pwd){
        xmppManager.getXmppUserManager().createAccount(username,pwd);
    }


}
