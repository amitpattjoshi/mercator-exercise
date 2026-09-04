package com.mercator.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CheckoutTest {

    private Checkout checkout;

    @BeforeEach
    public void setUp() {
        checkout = new Checkout();
    }

    @Test
    void testEmptyCartCostsZero() {
        assertEquals(0, checkout.calculateTotal(List.of()));
    }

    @Test
    void testNullItemsCartCostsZero() {
        assertEquals(0, checkout.calculateTotal(null));
    }
    @Test
    void testNullItemsInCartListThrows() {
        List<String> items = Arrays.asList("Apple", null);
        assertThrows(IllegalArgumentException.class,
                () -> checkout.calculateTotal(items));

    }
    @Test
    void testSingleAppleCosts60p() {
        assertEquals(60, checkout.calculateTotal(List.of("Apple")));
    }

    @Test
    void testSingleOrangeCost25p() {
        assertEquals(25, checkout.calculateTotal(List.of("Orange")));
    }

    @Test
    void testTwoOrangesCost50p() {
        assertEquals(50, checkout.calculateTotal(List.of("Orange", "Orange")));
    }

    @Test
    void testThreeApplesOneOrangeCosts145p() {
        assertEquals(145, checkout.calculateTotal(List.of("Apple", "Apple", "Apple", "Orange")));
    }

    @Test
    void testUnknownItemThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> checkout.calculateTotal(List.of("Banana")));
    }
    @Test
    void testBuyOneGetOneFreeOnAppleOffer() {
        assertEquals(60, checkout.calculateTotal(List.of("Apple", "Apple")));
    }
    @Test
    void testBuyThreeApplesCost120p() {
        assertEquals(120, checkout.calculateTotal(List.of("Apple", "Apple", "Apple")));
    }
    @Test
    void testBuyFiveApplesCost180p() {
        assertEquals(180, checkout.calculateTotal(List.of("Apple", "Apple", "Apple","Apple","Apple")));
    }
    @Test
    void testBuy3OrangesForCostOfTwoOffer() {
        assertEquals(50, checkout.calculateTotal(List.of("Orange", "Orange", "Orange")));
    }
    @Test
    void testBuy4OrangesCosts75p() {
        assertEquals(75, checkout.calculateTotal(List.of("Orange", "Orange", "Orange", "Orange")));
    }
    @Test
    void testBuy4Apples6OrangesCosts220p() {
        assertEquals(220, checkout.calculateTotal(List.of("Orange", "Orange","Orange","Orange","Apple", "Apple","Orange", "Apple","Apple","Orange")));
    }

}
