/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.hessian.demo;

import java.io.Serializable;
import java.util.Map;

/**
 * 类SerializModel.java的实现描述：TODO 类实现描述 
 * @author macun 2016年12月11日 下午9:09:32
 */
public class SerializModel implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6331222920608208019L;

    private String name;
    
    private Integer age;
    
    private Map<String,Object> map;

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    
    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    
    /**
     * @return the map
     */
    public Map<String, Object> getMap() {
        return map;
    }

    
    /**
     * @param map the map to set
     */
    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    
    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    
}
