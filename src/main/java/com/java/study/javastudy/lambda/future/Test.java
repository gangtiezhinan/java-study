package com.java.study.javastudy.lambda.future;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Classname Test
 * @Description
 * @Date 2020/4/14 10:51
 * @Author HXL
 */
public class Test {

    public static void main(String[] args) {
//        测试异步调用所花费时间
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        System.out.println(new Date());
//        findPrices("1000").stream().forEach(s -> System.out.println(s));
//        System.out.println(new Date());
//        响应 CompletableFuture 的 completion 事件
//        CompletableFuture[] futures = findPricesStream("myPhone")
//                .map(f -> f.thenAccept(System.out::println))
//                .toArray(CompletableFuture[]::new);
//        CompletableFuture.allOf(futures).join();
//        System.out.println(new Date());
//        微调测试
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream("myPhone27S")
                .map(f -> f.thenAccept(
                        s -> System.out.println(s + " (done in " +
                                ((System.nanoTime() - start) / 1_000_000) + " msecs)")))
                .toArray(CompletableFuture[]::new);
         CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in "
                + ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    //直接调用，商家商品折扣列表
//    public static List<String> findPrices(String product) {
//        return shops.stream()
//                .map(shop -> shop.getPrice(product))
////                .peek(x -> System.out.println("shop : " + x))
//                .map(Quote::parse)
////                .peek(x -> System.out.println("quote : " + x))
//                .map(Discount::applyDiscount)
////                .peek(x -> System.out.println("discount : " + x))
//                .collect(toList());
//    }

//异步调用商品返回折扣列表

    private static final Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 2),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    });
//名称中不带 Async
//的方法和它的前一个任务一样，在同一个线程中运行；而名称以 Async 结尾的方法会将后续的任
//务提交到一个线程池，所以每个任务是由不同的线程处理的
    public static List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getPrice(product), executor))
                        .map(future -> future.thenApply(Quote::parse))
                        .map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(
                                        () -> Discount.applyDiscount(quote), executor)))
                        .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
//-------------------------------------------------------------------------------------------------------------


    //重构 findPrices 方法返回一个由 Future 构成的流
    public static Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote ->
                        CompletableFuture.supplyAsync(
                                () -> Discount.applyDiscount(quote), executor)));
    }


}
