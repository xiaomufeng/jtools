/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.threadlocal.demo;


/**
 * 类AppContextHolder.java的实现描述：TODO 类实现描述 
 * @author macun 2016年12月8日 下午8:58:35
 */
public class AppContextHolder {

    private static ThreadLocal<AppContext> contextHolder = new ThreadLocal<AppContext>();
    
    public static void setAppContext(AppContext appContext){
        contextHolder.set(appContext);   
    }
    
    public static AppContext getAppContext(){
        if(null == contextHolder.get()){
            contextHolder.set(new AppContext());
        }
        
        return contextHolder.get();
    }
}
