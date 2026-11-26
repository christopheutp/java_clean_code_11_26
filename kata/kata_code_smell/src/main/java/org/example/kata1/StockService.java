package org.example.kata1;

public interface StockService {
    int getStockForItem(Item item);

    void updateStock(Item item, int quantity);
}
