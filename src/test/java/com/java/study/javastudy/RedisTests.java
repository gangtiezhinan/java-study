package com.java.study.javastudy;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Classname RedisTests
 * @Description
 * @Date 2020/4/17 11:14
 * @Author HXL
 */

@SpringBootTest
public class RedisTests {


    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private  RedisTemplate<String, String> redisTemplate;

    @Resource
    private ValueOperations<String,Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    /**
     * 测试redis一秒内的性能
     *
     * @return void
     * @throws
     * @date 2020/4/17 11:13
     * @author HXL
     */
    @Test
    void testRedis() {
        Jedis jedis = new Jedis("localhost", 6379, 100000);
        try {
            jedis.set("name", "likeai1");
            System.out.println(jedis.get("name"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    @Test
    @DisplayName("Test-Redis")
    void testRedis2() {
        stringRedisTemplate.opsForValue().set("myName", "luoyaping");
        System.out.println(stringRedisTemplate.opsForValue().get("myName"));
        assertEquals("luoyaping", stringRedisTemplate.opsForValue().get("myName"));
    }

    @Test
    void TestRedis3(){
        String lu = "luoyaping";
        redisTemplate.opsForValue().set("haha","luyaping");
        System.out.println(redisTemplate.opsForValue().get("haha"));
    }


    @Test
    void TestRedis4(){
        String message = "{\"imei\":\"868681046818066\",\"switch\":\"1\",\"current\":0,\"voltage\":245,\"power\":0,\"error\":\"1\",\"timeStamp\":1589490175}";
        redisTemplate.opsForList().leftPush("deviceStatus",message);
    }
}
