package org.example;

public record Order(int amount, boolean loyal, boolean express) {
    public Order {
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount");
        }
    }
}
