package miniboutique;

public record Product(String name, int unitPriceCents) {
    public Product {
        if (name == null || name.isBlank() || unitPriceCents < 0) {
            throw new IllegalArgumentException("Produit invalide");
        }
    }
}
