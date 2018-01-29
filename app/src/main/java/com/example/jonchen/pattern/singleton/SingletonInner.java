package com.example.jonchen.pattern.singleton;

/**
 * @author 17041931
 * @since 2018/1/26
 */

public class SingletonInner {

    private SingletonInner() {
    }

    public static SingletonInner getSingleton() {
        return SingletonHolder.singleton;
    }

    private static class SingletonHolder {
        private static final SingletonInner singleton = new SingletonInner();
    }
}
