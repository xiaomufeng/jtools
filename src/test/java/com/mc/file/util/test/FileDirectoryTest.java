/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.file.util.test;

import java.io.File;

/**
 * 类FileDirectoryTest.java的实现描述：TODO 类实现描述 
 * @author macun 2017年2月5日 下午6:08:13
 */
public class FileDirectoryTest {

    public static void main(String[] args){
        String path = "/Users/macun/work";
        File file = new File(path);
        getAllFiles(file,0);
    }
    
  //获取层级的方法
    public static String getLevel(int level)
    {
        //A mutable sequence of characters.
        StringBuilder sb=new StringBuilder();
        for(int l=0;l<level;l++)
        {
            sb.append("|--");
        }
        return sb.toString();
    }
    public static void getAllFiles(File dir,int level)
    {
        System.out.println(getLevel(level)+dir.getName());
        level++;
        File[] files=dir.listFiles();
        for(int i=0;i<files.length;i++)
        {
            if(files[i].isDirectory())
            {
                //这里面用了递归的算法
                getAllFiles(files[i],level);
            }
            else {
                System.out.println(getLevel(level)+files[i]);
            }   
        }        
    }
}
