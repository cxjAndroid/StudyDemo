package com.example.jonchen.pattern.strategy;

/**
 * @author jon
 * @since 3/16/18
 */

public class SubwayStragegy implements PriceStrategy {
    @Override
    public float calculatePrice(int mile) {
        if (mile < 10) {
            return 2f;
        } else if (mile > 10 && mile < 50) {
            return 2f + (mile - 10) * 0.1f;
        } else if (mile > 50) {
            return 8f;
        }
        return 0;
    }
}
