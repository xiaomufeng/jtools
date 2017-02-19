/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.file.util.test;

import com.mc.file.util.FileUtil;

/**
 * 类FileUtilTest.java的实现描述：TODO 类实现描述 
 * @author macun 2017年2月5日 下午5:53:04
 */
public class FileUtilTest {

    public static void main(String[] args){
        String path= "/Users/macun/macun";
        String fileName = "fileUtil1.txt";
        touchFile(path+"/"+fileName);
    }
    
    public static void touchFile(String fileName){
        FileUtil.touch(fileName);
    }
    
    public static boolean isFileExist(String fileName){
        return FileUtil.isFileExist(fileName);
    }
    
    public static void deleteFile(String fileName){
        
    }
    
    public static void createDirectory(String path){
        FileUtil.makeDirectory(path);
    }
}
