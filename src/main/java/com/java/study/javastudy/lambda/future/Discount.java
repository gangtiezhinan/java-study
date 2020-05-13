package com.java.study.javastudy.lambda.future;

import java.util.Random;

/**
 * @Classname Discount
 * @Description
 * @Date 2020/4/14 14:49
 * @Author HXL
 */
public class Discount {
    static Random random  = new Random();

    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
//        delay();
        randomDelay();
        return price * (100 - code.percentage) / 100;
    }


    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //随机休眠0.5-2.5秒
    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
