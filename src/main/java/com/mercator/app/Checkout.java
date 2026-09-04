package com.mercator.app;

import java.util.List;
import java.util.Map;

public class Checkout {

    private static final Map<String, Integer> PRICES = Map.of(
            "Apple", 60,
            "Orange", 25
    );

    public int calculateTotal(List<String> items) {

        if (items == null || items.isEmpty()) {
            return 0;
        }
        return items.stream()
                .mapToInt(this::priceOf)
                .sum();
    }

    private int priceOf(String item) {
        Integer price = PRICES.get(item);

        if (price == null) {
            throw new IllegalArgumentException("Unknown item " + item);
        }
        return PRICES.get(item);
    }
}
