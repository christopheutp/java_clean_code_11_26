package org.example.kata1;

public class OrderManager {
    private StockService stockService;
    private TaxService taxService;
    private NotificationService notificationService;

    public OrderManager(StockService stockService, TaxService taxService, NotificationService notificationService) {
        this.stockService = stockService;
        this.taxService = taxService;
        this.notificationService = notificationService;
    }

    public void processOrder(Order order) throws InvalidOrderException, InsufficientStockException {
        double subtotal = 0;
        validateOrder(order);
        /*double total = calculateTotal(order);
        checkStock(order);
        updateStock(order);*/
        for (Item item : order.getItems()) {
            int stock = stockService.getStockForItem(item);
            if (stock < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuffisant pour l'article : " + item.getName());
            }
            subtotal += item.getPrice() * item.getQuantity();
            double taxes = taxService.calculateTax(subtotal);
            subtotal += taxes;
            stockService.updateStock(item, item.getQuantity());
        }
        notificationService.sendOrderConfirmation(order, subtotal);
    }

    private void validateOrder(Order order) throws InvalidOrderException {
        if (order == null || order.getItems() == null || order.getItems().isEmpty()) {
            throw new InvalidOrderException("La commande est invalide");
        }
    }

    private double calculateTotal(Order order) {
        double subtotal = 0;
        for (Item item : order.getItems()) {
            subtotal += item.getPrice() * item.getQuantity();
        }
        double taxes = taxService.calculateTax(subtotal);
        return subtotal + taxes;
    }

    private void checkStock(Order order) throws InsufficientStockException {
        for (Item item : order.getItems()) {
            int stock = stockService.getStockForItem(item);
            if (stock < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuffisant pour l'article : " + item.getName());
            }
        }
    }

    private void updateStock(Order order) {
        for (Item item : order.getItems()) {
            stockService.updateStock(item, item.getQuantity());
        }
    }

}
