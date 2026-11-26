package org.example.kata2.polymorphisme;

public class ClothingProduct extends Product {
    private boolean isImported;

    public ClothingProduct(double basePrice, boolean isImported) {
        super(basePrice);
        this.isImported = isImported;
    }

    @Override
    public double calculatePrice() {
        double price = basePrice + basePrice * 0.2; // Taxe vêtements
        if (isImported) {
            price += price * 0.05; // Taxe d'importation
        }
        return price;
    }
}
