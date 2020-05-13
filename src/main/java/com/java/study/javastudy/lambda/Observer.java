package com.java.study.javastudy.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Observer
 * @Description
 * @Date 2020/3/26 11:14
 * @Author HXL
 */
public class Observer {


    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new Awatcher());
        feed.registerObserver(new Bwatcher());
        feed.registerObserver(new Cwatcher());
        feed.notifyObservers("A B is good");
    }



    interface Watcher {
        void notify(String sweet);
    }

    static class Awatcher implements Watcher {
        @Override
        public void notify(String sweet) {
            if (sweet != null && sweet.contains("A")){
                System.out.println("THIS IS A OF " + sweet);
            }
        }
    }

    static class Bwatcher implements Watcher {
        @Override
        public void notify(String sweet) {
            if (sweet != null && sweet.contains("B")){
                System.out.println("THIS IS B OF " + sweet);
            }
        }
    }

    static class Cwatcher implements Watcher {
        @Override
        public void notify(String sweet) {
            if (sweet != null && sweet.contains("C")){
                System.out.println("THIS IS C OF " + sweet);
            }
        }
    }



    interface Subject{
        void registerObserver(Watcher o);

        void notifyObservers(String tweet);
    }

    static class Feed implements Subject{
        private final List<Watcher> watcherList = new ArrayList<>();

        @Override
        public void registerObserver(Watcher o) {
            this.watcherList.add(o);
        }

        @Override
        public void notifyObservers(String tweet) {
           watcherList.forEach(o->o.notify(tweet));
        }
    }
}
