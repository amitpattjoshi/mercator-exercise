package com.mercator.app;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Checkout {

    private static final Map<String, PriceRule> PRICE_RULES = Map.of(
            "Apple", new BuyOneGetOneRule(60),
            "Orange", new ThreeForTwoRule(25)
    );

    private static Map<String, Long> groupByCartItem(List<String> items) {
        return items.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static void validateNullItems(List<String> items) {
        for (String item : items) {
            if (item == null) {
                throw new IllegalArgumentException("Cart contains a null item");
            }
        }
    }

    public int calculateTotal(List<String> items) {

        if (items == null) {
            return 0;
        }
        validateNullItems(items);
        Map<String, Long> countsByItem = groupByCartItem(items);

        return countsByItem.entrySet().stream()
                .mapToInt(entry ->
                        this.priceOf(entry.getKey(),
                                entry.getValue().intValue()))
                .sum();
    }

    private int priceOf(String item, int quantity) {
        PriceRule priceRule = PRICE_RULES.get(item);

        if (priceRule == null) {
            throw new IllegalArgumentException("Unknown item " + item);
        }
        return priceRule.priceFor(quantity);
    }
}
