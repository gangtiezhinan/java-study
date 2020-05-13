package com.java.study.javastudy.lambda;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @Classname Factory
 * @Description
 * @Date 2020/3/28 15:17
 * @Author HXL
 */
public class Factory {

    //简单工厂模式

    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product createProduct(String name){
        Supplier<Product> p = map.get(name);
        if(p != null) {
            return p.get();
        }
        throw new IllegalArgumentException("No such product " + name);
    }

    public static void main(String[] args) {

       createProduct("loan");

    }

    static class Loan extends Product{

        public Loan() {

        }
    }

    static class Stock extends Product{
        public Stock() {
        }
    }


    static class Bond extends Product{
        public Bond() {
        }
    }

    static class Product{

        public Product() {
        }
    }
}
