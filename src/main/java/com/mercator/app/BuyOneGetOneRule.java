package com.mercator.app;

public record BuyOneGetOneRule(int unitPrice) implements PriceRule{
    @Override
    public int priceFor(int quantity) {
        int chargeableUnit = quantity - (quantity / 2);
        return chargeableUnit * unitPrice;
    }
}
