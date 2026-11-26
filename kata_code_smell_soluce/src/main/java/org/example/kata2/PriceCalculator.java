package org.example.kata2;

public class PriceCalculator {
    public double calculatePrice(Product product) {
        if (product.getType() == null) return product.getBasePrice();
        org.example.kata2.polymorphisme.Product pricedProduct = switch (product.getType()) {
            case ELECTRONICS ->
                    new org.example.kata2.polymorphisme.ElectronicsProduct(product.getBasePrice(), product.isOnSale());
            case BOOK -> new org.example.kata2.polymorphisme.BookProduct(product.getBasePrice());
            case CLOTHING ->
                    new org.example.kata2.polymorphisme.ClothingProduct(product.getBasePrice(), product.isImported());
        };
        return pricedProduct.calculatePrice();
    }


//    public double calculatePrice(Product product) {
//        double price = product.getBasePrice();
//        if (product.getType() == ProductType.ELECTRONICS) {
//            price += price * 0.15; // Taxe électronique
//            if (product.isOnSale()) {
//                price -= price * 0.1; // Remise de 10%
//            }
//        } else if (product.getType() == ProductType.BOOK) {
//            price += price * 0.05; // Taxe livre
//        } else if (product.getType() == ProductType.CLOTHING) {
//            price += price * 0.2; // Taxe vêtements
//            if (product.isImported()) {
//                price += price * 0.05; // Taxe d'importation
//            }
//        }
//        // Autres types de produits
//        return price;
//    }
}
