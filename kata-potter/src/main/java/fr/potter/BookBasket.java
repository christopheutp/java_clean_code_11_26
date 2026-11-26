package fr.potter;

public class BookBasket {
    private final int[] quantities = new int[5];

    public void addBook(int volume, int quantity) {
        if (volume < 1 || volume > 5 || quantity < 1) {
            throw new IllegalArgumentException("Tome ou quantité invalide");
        }
        quantities[volume - 1] += quantity;
    }

    public int[] getQuantities() {
        return quantities.clone();
    }
}
