/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the confidential and proprietary information of
 * Alibaba.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Wuage.com.
 */
package com.mc.hessian.demo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * 类SerializUtil.java的实现描述：TODO 类实现描述
 * 
 * @author macun 2016年12月10日 下午10:51:27
 */
public class SerializUtil {

    private static final String ENCODE = "ISO-8859-1";

    public static String serializationByHessian(Object serializable) {
        if (serializable == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        try {
            ho.writeObject(serializable);
            return new String(os.toByteArray(), ENCODE);

        } catch (IOException e) {
//            LOGGER.error("SerializUtils.Serialization is error serializable is" + serializable.toString(), e);
        } finally {
//            os.close(); 
        }
        return null;
    }
    
    public static String serializationByJdk(Object serializable) {
        if (serializable == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream ho;
        try {
            ho = new ObjectOutputStream(os);
            ho.writeObject(serializable);
            return new String(os.toByteArray(), ENCODE);

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        return null;
    }
   

    public static Object antiSerializationByHessian(String value) {
        if (value == null) {
            return null;
        }
        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(value.getBytes(ENCODE));
            HessianInput hi = new HessianInput(is);
            Object obj;
            obj = hi.readObject();
            return obj;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    @SuppressWarnings("unchecked")
    public static <T> T antiSerializationByHessian(String value, Class<T> t) {
        if (value == null) {
            return null;
        }
        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(value.getBytes(ENCODE));
            HessianInput hi = new HessianInput(is);
            return (T) hi.readObject(t);
        } catch (IOException e) {
            e.printStackTrace();
            // LOGGER.error("SerializUtils.antiSerialization is error value is" + value, e);
        } finally {
//            IOUtils.close(is);
        }
        return null;
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T antiSerializationByJdk(String value, Class<T> t) {
        if (value == null) {
            return null;
        }
        ByteArrayInputStream is = null;
        try {
            is = new ByteArrayInputStream(value.getBytes(ENCODE));
            ObjectInputStream hi = new ObjectInputStream(is);
            return (T) hi.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            // LOGGER.error("SerializUtils.antiSerialization is error value is" + value, e);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
//            IOUtils.close(is);
        }
        return null;
    }
    
    public static void main(String[] args){
        
//        testHessian();
//        testJdk();
//        testCompatibility();
//        printSerialize();
        performanceTest();
        
    }
    
    public static void performanceTest(){
        Long time1 = System.currentTimeMillis();
        for(int i = 0 ;i<50000;i++){
            testJdk();
//            testHessian();
        }
        Long time2 = System.currentTimeMillis();
//        for(int i = 0 ;i<10000;i++){
//            testHessian();
//        }
        Long time3 = System.currentTimeMillis();
        System.out.println(time2-time1);
        System.out.println(time3-time2);
    }
    
    public static void testHessian(){
        SerializModel model = new SerializModel();
        model.setAge(10);
        model.setName("小明hello");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("aa", "第一");
        model.setMap(map);
        
        String serializString = SerializUtil.serializationByHessian(model);
        
        SerializModel newModel = SerializUtil.antiSerializationByHessian(serializString, SerializModel.class);
        
//        System.out.println(newModel.getAge());
//        System.out.println(newModel.getName());
//        Map<String,Object> newMap = newModel.getMap();
//        System.out.println(newMap.get("aa"));
    }
    
    public static void testJdk(){
        SerializModel model = new SerializModel();
        model.setAge(10);
        model.setName("小明hello");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("aa", "第一");
        model.setMap(map);
        
        String serializString = SerializUtil.serializationByJdk(model);
        
        SerializModel newModel = SerializUtil.antiSerializationByJdk(serializString, SerializModel.class);
        
//        System.out.println(newModel.getAge());
//        System.out.println(newModel.getName());
//        Map<String,Object> newMap = newModel.getMap();
//        System.out.println(newMap.get("aa"));
    }
    
    public static void testCompatibility(){
        SerializModel model = new SerializModel();
        model.setAge(10);
        model.setName("小明hello");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("aa", "第一");
        model.setMap(map);
        
        String serializString = SerializUtil.serializationByJdk(model);
        
        SerializModel newModel = SerializUtil.antiSerializationByHessian(serializString, SerializModel.class);
        
        System.out.println(newModel.getAge());
        System.out.println(newModel.getName());
        Map<String,Object> newMap = newModel.getMap();
        System.out.println(newMap.get("aa"));
    }
    
    public static void printSerialize(){
        SerializModel model = new SerializModel();
        model.setAge(10);
        model.setName("小明hello");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("aa", "第一");
        model.setMap(map);
        
        String serializString = SerializUtil.serializationByJdk(model);
        System.out.println(serializString);
        System.out.println("/n");
        String serializString1 = SerializUtil.serializationByJdk(model);
        System.out.println(serializString1);
        
        if(serializString.equals(serializString1)){
            System.out.println("=====");
        }
    }
    
    
}
