package com.example.jonchen.pattern.singleton;

/**
 * @author 17041931
 * @since 2018/1/25
 */

public class Singleton1 {
    private static final Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getSingleton() {
        return singleton;
    }
}
