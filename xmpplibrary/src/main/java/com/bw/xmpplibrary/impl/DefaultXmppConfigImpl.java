package com.bw.xmpplibrary.impl;


import com.bw.xmpplibrary.contract.IXmppConfig;

/**
 * 默认XmppConfig实现类
 */
public class DefaultXmppConfigImpl implements IXmppConfig {
    @Override
    public String getDomainName() {
        return "zhangyue";
    }

    @Override
    public String getHostAddress() {
        return "39.98.153.96";
    }

    @Override
    public int getPort() {
        return 5222;
    }
}
