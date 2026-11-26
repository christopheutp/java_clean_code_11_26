package org.example.kata2.polymorphisme;

public class ElectronicsProduct extends Product {
    private boolean onSale;

    public ElectronicsProduct(double basePrice, boolean onSale) {
        super(basePrice);
        this.onSale = onSale;
    }

    @Override
    public double calculatePrice() {
        double price = basePrice + basePrice * 0.15; // Taxe électronique
        if (onSale) {
            price -= price * 0.1; // Remise de 10%
        }
        return price;
    }
}
