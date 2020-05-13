package com.java.study.javastudy.lambda;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Classname Chain
 * @Description
 * @Date 2020/3/28 15:16
 * @Author HXL
 */
public class Chain {
    public static void main(String[] args) {
        //简单责任链模式
        UnaryOperator<String> one = (String text)->"we can go home today " + text;
        UnaryOperator<String> two = (String text)->text.replace("you","leader");
        Function<String,String> chain = one.andThen(two);
        System.out.println(chain.apply(", but you are sb"));
    }

}
