package com.java.study.javastudy.lambda.service;

/**
 * @Classname ITest
 * @Description
 * @Date 2020/4/3 16:07
 * @Author HXL
 */
public interface ITestService {


    default void test1() {
        System.out.println("现在开始执行默认方法");
    }



}
