package org.example;

import java.util.List;

public class OrderService {
    public int CalculateTotal(Order order) {
        int Shipping_Fee = 5;
        if (order.express()) Shipping_Fee = 12;
        return order.amount() + Shipping_Fee;
    }
}
