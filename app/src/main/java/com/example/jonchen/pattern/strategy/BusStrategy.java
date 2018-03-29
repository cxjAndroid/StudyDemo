package com.example.jonchen.pattern.strategy;

/**
 * @author jon
 * @since 3/16/18
 */

public class BusStrategy implements PriceStrategy {
    @Override
    public float calculatePrice(int mile) {
        return 2f;
    }
}
