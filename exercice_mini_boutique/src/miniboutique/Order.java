package miniboutique;

import java.util.List;

public record Order(String customerEmail, List<Product> products) {
    public Order {
        if (customerEmail == null || customerEmail.isBlank() || products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Commande invalide");
        }
        products = List.copyOf(products);
    }
}
