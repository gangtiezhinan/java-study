package com.java.study.javastudy.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Classname Default  默认方法
 * @Description
 * @Date 2020/4/3 15:47
 * @Author HXL
 */
public class Default {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 5, 1, 2, 6);
        numbers.sort(Comparator.naturalOrder());
        System.out.println(numbers);
    }
}
