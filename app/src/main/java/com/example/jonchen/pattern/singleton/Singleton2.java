package com.example.jonchen.pattern.singleton;

/**
 * @author 17041931
 * @since 2018/1/25
 */

public class Singleton2 {
    private static Singleton2 singleton2;

    private Singleton2() {
    }

    public Singleton2 getSingleton2() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }

}
