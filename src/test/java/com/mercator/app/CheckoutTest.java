package com.mercator.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testSingleAppleCosts60p() {
        assertEquals(60, checkout.calculateTotal(List.of("Apple")));
    }

    @Test
    void testSingleOrangeCost25p() {
        assertEquals(25, checkout.calculateTotal(List.of("Orange")));
    }

    @Test
    void testTwoApplesCost120p() {
        assertEquals(120, checkout.calculateTotal(List.of("Apple", "Apple")));
    }

    @Test
    void testTwoOrangesCost50p() {
        assertEquals(50, checkout.calculateTotal(List.of("Orange", "Orange")));
    }

    @Test
    void testThreeApplesOneOrangeCosts205p() {
        assertEquals(205, checkout.calculateTotal(List.of("Apple", "Apple", "Apple", "Orange")));
    }

    @Test
    void testUnknownItemThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> checkout.calculateTotal(List.of("Banana")));
    }

}
