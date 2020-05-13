package com.java.study.javastudy.lambda.future;

import java.util.Random;

/**
 * @Classname Shop
 * @Description
 * @Date 2020/4/13 16:15
 * @Author HXL
 */
public class Shop {

    //    public static void main(String[] args) throws Exception {
//        System.out.println(new Date());
//        CompletableFuture<Integer> longFuncTrue =  getPriceAsync("bububu");
//        System.out.println();
//        System.out.println(new Date());
//        //            线程等待一秒钟。这一段注释与否所耗费的时间一样，因为是异步
//        try {
//            Thread.sleep(1000L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println(longFuncTrue.join());
//        System.out.println(new Date());
//    }
//    public static CompletableFuture<Integer> getPriceAsync(String product) {
//        CompletableFuture<Integer> futurePrice = new CompletableFuture<>();
//        new Thread( () -> {
//            try {
//                int price = 0;
//                for (int i = 1; i < 1000000000; i++) {
//                    price += i;
//                }
////            线程等待一秒钟
//                try {
//                    Thread.sleep(1000L);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                futurePrice.complete(price);
//            }catch (Exception e){
//                futurePrice.completeExceptionally(e);
//            }
//        }).start();
//        return futurePrice;
//    }
    static Random random  = new Random();

    private String name;


    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", name, price, code);
    }

    private double calculatePrice(String product) {
//        delay();
        randomDelay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
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
