package org.example.kata1;

public class Order {
    private final java.util.List<Item> items;

    public Order(java.util.List<Item> items) {
        this.items = items;
    }

    public java.util.List<Item> getItems() {
        return items;
    }
}
