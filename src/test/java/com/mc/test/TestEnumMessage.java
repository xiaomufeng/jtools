/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.test;

import com.mc.common.enums.support.EnumTemplateInfo;
import com.mc.common.enums.support.TemplateInfoHelper;

/**
 * 类TestEnumMessage.java的实现描述：TODO 类实现描述 
 * @author macun 2016年11月28日 下午5:22:56
 */
public enum TestEnumMessage implements EnumTemplateInfo{
    /**
     * 业务类型无效
     */
    BIZ_TYPE_IS_INVALID,
    /**
     * 商品不存在
     */
    PRODUCT_STATUS_ERROR;
    
    public String toCode() {
//        return StringUtil.toUpperCase(this.name());
        return this.name();
    }

    public static TestEnumMessage parse(String error) {
        
        for (TestEnumMessage code : TestEnumMessage.values()) {
            if (code.toCode().equalsIgnoreCase(error)) {
                return code;
            }
        }
        return null;
    }
    
    
    
    /* (non-Javadoc)
     * @see com.mc.common.enums.support.EnumMessage#getMessage()
     */
    @Override
    public String getMessage() {
        try {
            return TemplateInfoHelper.getMessage(this);
        } catch (Exception e) {
            return this.toCode();
        }
    }

    /* (non-Javadoc)
     * @see com.mc.common.enums.support.EnumMessage#getMessage(java.lang.String[])
     */
    @Override
    public String getMessage(String... param) {
        // TODO Auto-generated method stub
        return null;
    }

}
