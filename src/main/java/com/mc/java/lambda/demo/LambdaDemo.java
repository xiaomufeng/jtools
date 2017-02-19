/*
 * Copyright 2015-2020 wuage.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Wuage.com.
 */
package com.mc.java.lambda.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


/**
 * 类LambdaDemo.java的实现描述：TODO 类实现描述 
 * @author macun 2017年2月19日 下午7:55:57
 */
public class LambdaDemo {

    public static void main(String[] args){
        Integer[] datas = {1,5,3,8,9};
        
        List<Integer> waitSort = new ArrayList<>();
        waitSort= Arrays.asList(datas);
        long count = waitSort.stream().filter(num -> num>3).count();
//        Stream.of(waitSort).forEach(num -> System.out.println(num));
        System.out.println(count);
        
        String[] strings = {"habc","fdef","opq","afd","opf"};
        
        Stream.of(strings).forEach(param->{System.out.println(param);});
        
        Arrays.sort(strings, (v1,v2)->Integer.compare(v1.length(), v2.length()));
        Stream.of(strings).forEach(param->System.out.println(param));
    }
}
