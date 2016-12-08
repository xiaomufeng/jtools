/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.common.enums.support;

/**
 * 类EnumMessage.java的实现描述：TODO 类实现描述 
 * @author macun 2016年11月28日 下午5:21:32
 */
public interface EnumTemplateInfo {


    /** 得到此错误码对应的中文描述 */
    public String getMessage();

    /** 得到此错误码对应的中文描述,支持传入字符串替换对应点位符:{0},{1}... */
    public String getMessage(String... param);

}
