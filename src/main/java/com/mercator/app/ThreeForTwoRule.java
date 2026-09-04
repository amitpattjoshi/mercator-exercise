package com.mercator.app;

public record ThreeForTwoRule(int unitPrice) implements PriceRule {
    @Override
    public int priceFor(int quantity) {
        int chargeableQuantity = quantity - (quantity /3);
        return chargeableQuantity * unitPrice;
    }
}
