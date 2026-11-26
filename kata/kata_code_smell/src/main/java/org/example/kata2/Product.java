package org.example.kata2;

public class Product {
    private final ProductType type;
    private final double basePrice;
    private final boolean onSale, imported;

    public Product(ProductType type, double basePrice, boolean onSale, boolean imported) {
        this.type = type;
        this.basePrice = basePrice;
        this.onSale = onSale;
        this.imported = imported;
    }

    public ProductType getType() {
        return type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public boolean isImported() {
        return imported;
    }
}
