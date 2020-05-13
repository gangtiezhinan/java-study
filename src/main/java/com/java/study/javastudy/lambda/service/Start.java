package com.java.study.javastudy.lambda.service;



import javax.annotation.Resource;

/**
 * @Classname Start
 * @Description
 * @Date 2020/4/3 16:30
 * @Author HXL
 */
public class Start {

    @Resource
    private static ITestService iTestService;

    public static void main(String[] args) {

        iTestService.test1();
    }
}
