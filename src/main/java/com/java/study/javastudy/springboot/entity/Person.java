package com.java.study.javastudy.springboot.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.lang.NonNull;
import sun.plugin2.message.Message;

import javax.validation.constraints.NotEmpty;

/**
 * @Classname Person
 * @Description
 * @Date 2020/5/19 14:58
 * @Author HXL
 */

public class Person {


    @NotEmpty(message = "名称不能为空!!")
    private String name;


    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
