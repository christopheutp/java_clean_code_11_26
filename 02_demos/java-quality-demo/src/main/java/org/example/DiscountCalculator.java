package org.example;

public class DiscountCalculator {
    public int discount(Order order) {
        if (order.loyal() && order.amount() >= 100) {
            return 20;
        }
        return 0;
    }
}
