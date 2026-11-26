package org.example.kata2;

public class PriceCalculator {
    public double calculatePrice(Product product) {
        double price = product.getBasePrice();
        if (product.getType() == ProductType.ELECTRONICS) {
            price += price * 0.15; // Taxe électronique
            if (product.isOnSale()) {
                price -= price * 0.1; // Remise de 10%
            }
        } else if (product.getType() == ProductType.BOOK) {
            price += price * 0.05; // Taxe livre
        } else if (product.getType() == ProductType.CLOTHING) {
            price += price * 0.2; // Taxe vêtements
            if (product.isImported()) {
                price += price * 0.05; // Taxe d'importation
            }
        }
        // Autres types de produits
        return price;
    }
}
