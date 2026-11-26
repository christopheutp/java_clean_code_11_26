package org.example.kata2.polymorphisme;

public class BookProduct extends Product {
    public BookProduct(double basePrice) {
        super(basePrice);
    }

    @Override
    public double calculatePrice() {
        return basePrice + basePrice * 0.05; // Taxe livre
    }
}
