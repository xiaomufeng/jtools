/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Wuage.com.
 */
package com.mc.threadlocal.demo;

/**
 * 类AppContext.java的实现描述：TODO 类实现描述
 * 
 * @author macun 2016年12月8日 下午8:58:03
 */
public class AppContext {

    private String userName;

    private String password;

    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
