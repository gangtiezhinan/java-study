package com.java.study.javastudy.springboot.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description
 * @Date 2020/5/15 16:48
 * @Author HXL
 */
@RestController
@RequestMapping("/test")
@ConfigurationProperties(prefix = "test")
@Component
public class HelloController {


    private String msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @RequestMapping("/msg")
    String hello() {
        return this.getMsg();
    }
}
