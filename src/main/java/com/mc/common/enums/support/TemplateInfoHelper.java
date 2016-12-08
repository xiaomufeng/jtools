/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.common.enums.support;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;



/**
 * 类MessageHelper.java的实现描述：TODO 类实现描述 
 * @author macun 2016年11月28日 下午4:03:40
 */
public class TemplateInfoHelper {

    private final static String                     POINT_REGEX  = "\\.";
    private final static Map<String, MessageHolder> msgHolderMap = new ConcurrentHashMap<String, MessageHolder>();

    /**
     * 根据枚举获得对应同名xml文件中key对应的message消息
     * 
     * @param e 枚举实例
     * @return
     */
    public static <E extends Enum<E>> String getMessage(Enum<E> e) {
        if (e == null) {
            return null;
        }
        MessageHolder msgHolder = getMessageHolder(e);
        if (msgHolder == null) {
            return null;
        }
        return msgHolder.getMessage(e);
    }

    /**
     * 根据枚举获得对应同名xml文件中key对应的message消息
     * 
     * @param e 枚举实例
     * @param param 点位字符串
     * @return
     */
    public static <E extends Enum<E>> String getMessage(Enum<E> e, String[] param) {
        if (e == null) {
            return null;
        }
        MessageHolder msgHolder = getMessageHolder(e);
        if (msgHolder == null) {
            return null;
        }
        return msgHolder.getMessage(e, param);
    }

    // 根据枚举获取对应的messageHolder
    private static <E extends Enum<E>> MessageHolder getMessageHolder(Enum<E> e) {
        MessageHolder msgHolder = null;
        if (msgHolderMap.containsKey(e.getDeclaringClass().getCanonicalName())) {
            return msgHolderMap.get(e.getDeclaringClass().getCanonicalName());
        } else {
            try {
                msgHolder = createTypePathHolder(e.getDeclaringClass());
            } catch (Exception e1) {
                throw new RuntimeException(e1.getMessage(), e1);
            }
            if (msgHolder != null) {
                msgHolderMap.put(e.getDeclaringClass().getCanonicalName(), msgHolder);
            }
        }
        return msgHolder;
    }

    /**
     * 创建一个MessageHolder<br>
     * 
     * <pre>
     * 将从classpath根目录查找以类名命名的的xml文件作为资源文件。
     * </pre>
     * 
     * @param type 指定的类型
     * @return
     */
    static MessageHolder createDefaultHolder(Class<?> type) {
        return new MessageHolder(type);
    }

    /**
     * 创建一个MessageHolder<br>
     * 
     * <pre>
     * 将从参数指定的classpath根目录中查找以类名命名的的xml文件作为资源文件。
     * </pre>
     * 
     * @param type 指定的类型
     * @param String 执行的资源文件路径
     * @return
     */
    static MessageHolder createSpecialPathHolder(Class<?> type, String classpath) {
        return new MessageHolder(type, classpath);
    }

    /**
     * 创建一个MessageHolder<br>
     * 
     * <pre>
     * 将从type类同一级别目录下查找以类名命名的xml文件作为资源文件。
     * </pre>
     * 
     * @param type 指定的类型
     * @return
     */
    static MessageHolder createTypePathHolder(Class<?> type) {
        String path = type.getPackage().getName();
        if (path == null || "".equals(path.trim())) {
            return createDefaultHolder(type);
        }
        path = path.replaceAll(POINT_REGEX, "/");
        return new MessageHolder(type, path);
    }
    
    
    public static class MessageHolder {
        private Class<?>       type;
        private String         resourcePath;
        private ResourceBundle resourceBundle;

        /**
         * 从指定路径加载resource
         * 
         * @param type
         * @param classpath
         */
        MessageHolder(Class<?> type, String classpath){
            this.type = type;
            this.resourcePath = classpath;
            loadBundleBySpecialPath();
        }

        /**
         * 从默认路径加载resource
         * 
         * @param type
         */
        MessageHolder(Class<?> type){
            this.type = type;
            loadBundleByDefault();
        }

        private void loadBundleByDefault() {
            resourceBundle = XmlResourceBundleFactory.getBundle(type.getName());
        }

        private void loadBundleBySpecialPath() {
            resourceBundle = XmlResourceBundleFactory.getBundle(resourcePath, type.getName());
        }

        public String getMessage(Enum<?> e) {
            if (resourceBundle == null) {
                return null;
            }
            return resourceBundle.getString(e.name());
        }

        public String getMessage(String key) {
            if (resourceBundle == null) {
                return null;
            }
            return resourceBundle.getString(key);
        }

        public String getMessage(Enum<?> e, String... param) {
            String s = getMessage(e);
            if (s == null) {
                return null;
            }
            return MessageFormat.format(s, (Object[]) param);
        }

        public String getMessage(String key, String... param) {
            String s = getMessage(key);
            if (s == null) {
                return null;
            }
            return MessageFormat.format(s, (Object[]) param);
        }
    }
}
