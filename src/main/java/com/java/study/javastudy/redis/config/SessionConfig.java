package com.java.study.javastudy.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Classname SessionConfig
 * @Description
 * @Date 2020/4/17 15:45
 * @Author HXL
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 10)
public class SessionConfig {
}
