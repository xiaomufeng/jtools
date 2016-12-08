/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.threadlocal.demo;

/**
 * 类FooFilter.java的实现描述：模仿web filter
 * @author macun 2016年12月8日 下午8:58:54
 */
public class FooFilter {

    public void doFilter(String servletRequest,String servletResponsed,String filterChain){
        servletRequest="servletRequest.getParameter();";
        
        AppContext context = new AppContext();
        context.setPassword("pass");
        context.setUserName("demo");
        
        AppContextHolder.setAppContext(context);
        
        filterChain="filterChain.doFilter()";
    }
}
