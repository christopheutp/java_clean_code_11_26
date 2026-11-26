package org.example.kata2.polymorphisme;

public abstract class Product {
    protected double basePrice;

    public Product(double basePrice) {
        this.basePrice = basePrice;
    }

    public abstract double calculatePrice();
}
