package org.example;

public class Main {
    public static void main(String[] args) {
        Order order = new Order(120, true, false);
        System.out.println(new OrderService().CalculateTotal(order));
        System.out.println(new DiscountCalculator().discount(order));
        System.out.println(new PaymentService().isCard(new String("CARD")));
    }
}
