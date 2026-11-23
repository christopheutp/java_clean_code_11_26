package com.example.partie1;

public class DemoOrderCleanCode {
    public void handleOrder(Order order) {
        if (order.getItems().isEmpty()) { // valider
            throw new IllegalArgumentException("Empty order");
        }
        double total = 0; // calculer
        for (Item item : order.getItems()) {
            total += item.getPrice();
        }
        System.out.println("Total: " + total); // afficher
    }

    public void handleOrderVClean(Order order) {
        validateOrder(order);
        double total = calculateTotal(order);
        printTotal(total);
    }

    private void validateOrder(Order order) { // commande vide rejetée
        if (order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Empty order");
        }
    }

    private double calculateTotal(Order order) { // total correct
        return order.getItems().stream().mapToDouble(Item::getPrice).sum();
    }

    private void printTotal(double total) { // affcihage
        System.out.println("Total: " + total);
    }

}
