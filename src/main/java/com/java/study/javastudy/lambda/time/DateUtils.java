package com.java.study.javastudy.lambda.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * @Classname DateUtils
 * @Description
 * @Date 2020/4/15 16:17
 * @Author HXL
 */
public class DateUtils {

    public static void main(String[] args) {
//        LocalDate today = LocalDate.now();
//        int year = today.get(ChronoField.YEAR);
//        int month = today.get(ChronoField.MONTH_OF_YEAR);
//        int day = today.get(ChronoField.DAY_OF_MONTH);
//        System.out.println(year);
//        System.out.println(month);
//        System.out.println(day);

        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(4, -1_000_000_000));
        System.out.println(Instant.now());
    }
}
