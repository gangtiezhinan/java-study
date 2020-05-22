package com.java.study.javastudy.springboot.config;

import com.java.study.javastudy.springboot.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname BeanConfig
 * @Description
 * @Date 2020/5/19 14:56
 * @Author HXL
 */
@Configuration
public class BeanConfig {

    @Bean(name = "bill")
    public Person person1(){
        return new Person("Bill Gates",62);
    }

    @Bean("linus")
    public Person person2(){
        return new Person("Linus",48);
    }
}
