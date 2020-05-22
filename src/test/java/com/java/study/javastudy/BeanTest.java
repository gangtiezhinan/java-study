package com.java.study.javastudy;

import com.java.study.javastudy.springboot.config.BeanConfig;
import com.java.study.javastudy.springboot.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @Classname BeanTest
 * @Description
 * @Date 2020/5/19 14:59
 * @Author HXL
 */
@SpringBootTest
public class BeanTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);


    @Test
     void beanList(){
        Map<String, Person> map = applicationContext.getBeansOfType(Person.class);
        System.out.println(map);
    }
}
