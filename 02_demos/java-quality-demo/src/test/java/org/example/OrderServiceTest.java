package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {
    @Test
    void standardShipping() {
        assertEquals(105, new OrderService().CalculateTotal(new Order(100, false, false)));
    }
}
