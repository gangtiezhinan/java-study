package com.java.study.javastudy.redis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @Classname RedisConroller
 * @Description
 * @Date 2020/4/17 10:26
 * @Author HXL
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理测试版")
public class RedisController {

    @RequestMapping("")
    public void testRedis() {

    }


    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    @Cacheable(value = "user-key")
    @ApiOperation("测试redis缓存")
    public String getUser() {
        String user = "罗亚平";
        System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
        return user;
    }


    @RequestMapping(value = "/uid",method = RequestMethod.GET)
    @ApiOperation("测试redis的session缓存")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }
}
