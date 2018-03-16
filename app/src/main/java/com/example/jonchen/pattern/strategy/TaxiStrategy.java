package com.example.jonchen.pattern.strategy;

/**
 * @author jon
 * @since 3/16/18
 */

public class TaxiStragegy implements PriceStrategy {
    @Override
    public float calculatePrice(int mile) {
        if (mile < 3) {
            return 10f;
        } else if (mile > 3) {
            return 10f + (mile - 3) * 5f;
        }
        return 0;
    }
}
