package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCalculatorTest {
    @Test
    void loyalCustomerGetsDiscount() {
        assertEquals(20, new DiscountCalculator().discount(new Order(120, true, false)));
    }
}
