package com.java.study.javastudy.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HelloController
 * @Description
 * @Date 2020/5/15 16:48
 * @Author HXL
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "Hello World";
    }
}
