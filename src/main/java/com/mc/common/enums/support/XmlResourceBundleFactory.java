/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.common.enums.support;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;


/**
 * 类XmlResourceBundleFactory.java的实现描述：TODO 类实现描述 
 * @author macun 2016年11月28日 下午5:16:11
 */
public class XmlResourceBundleFactory {

    private static XMLResourceBundleControl DEFAULT_CONTROL = new XMLResourceBundleControl(null, true);
    private final static String             POINT_REGEX     = "\\.";

    /**
     * 通过默认的XMLResourceBundleControl获取资源Bundle
     * 
     * <pre>
     * 默认的XMLResourceBundleControl实现，就在classpath的跟目录下查找资源文件。
     * </pre>
     * 
     * @param fileName
     * @return
     */
    public static ResourceBundle getBundle(String fileName) {
        return ResourceBundle.getBundle(fileName, DEFAULT_CONTROL);
    }

    /**
     * 通过指定path的XMLResourceBundleControl获取资源Bundle
     * 
     * <pre>
     * 指定path的XMLResourceBundleControl实现，会在指定的classpath目录下查找资源文件。
     * </pre>
     * 
     * @param fileName
     * @param classpath
     * @return
     */
    public static ResourceBundle getBundle(String classpath, String fileName) {
        XMLResourceBundleControl xmlResourceBundleControl = new XMLResourceBundleControl(classpath, true);
        return ResourceBundle.getBundle(fileName, xmlResourceBundleControl);
    }

    public static class XMLResourceBundleControl extends ResourceBundle.Control {

        private String resourcePath; // 資源的classpath路径

        private boolean seprateDotPrefix; // 用于标识是否把包含点的baseName前缀过滤掉。

        XMLResourceBundleControl(String resourcePath, boolean seprateDotPrefix) {
            this.resourcePath = resourcePath;
            this.seprateDotPrefix = seprateDotPrefix;
        }

        public List<String> getFormats(String baseName) {
            return Collections.singletonList("xml");
        }

        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader,
                                        boolean reload) throws IllegalAccessException, InstantiationException,
                                                        IOException {

            if ((baseName == null) || (locale == null) || (format == null) || (loader == null)) {
                throw new NullPointerException();
            }
            ResourceBundle bundle = null;
            if (!format.equals("xml")) {
                return null;
            }

            if (seprateDotPrefix) {
                if (baseName.indexOf(".") > 0) {
                    String array[] = baseName.split(POINT_REGEX);
                    baseName = array[array.length - 1];
                }
            }
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, format);
            String resourceFullName = resourceName;
            if (resourcePath != null && !"".equals(resourcePath.trim())) {// baseName可能包含包命前綴，把包命前綴去除
                resourceFullName = resourcePath + "/" + resourceName;
            }
            URL url = loader.getResource(resourceFullName);
            if (url == null) {
                return null;
            }
            URLConnection connection = url.openConnection();
            if (connection == null) {
                return null;
            }
            if (reload) {
                connection.setUseCaches(false);
            }
            InputStream stream = connection.getInputStream();
            BufferedInputStream bis = null;
            try {
                bis = new BufferedInputStream(stream);
                bundle = new XMLResourceBundle(bis);
            } catch (Exception e) {
            } finally {
                if (bis != null) {
                    bis.close();
                }
            }
            return bundle;
        }
    }

    static class XMLResourceBundle extends ResourceBundle {

        private Properties props;

        XMLResourceBundle(InputStream stream) throws IOException {
            props = new Properties();
            props.loadFromXML(stream);
        }

        protected Object handleGetObject(String key) {
            return props.getProperty(key);
        }

        public Enumeration<String> getKeys() {
            Set<String> handleKeys = props.stringPropertyNames();
            return Collections.enumeration(handleKeys);
        }
    }

    public static void main(String[] args) {
        String s = "aa.vv.cc";
        System.out.println(s.split("\\.")[2]);
    }
}
