package miniboutique.presentation;

import miniboutique.Order;

public class ReceiptPrinter {
    public void print(Order order, int total) {
        System.out.println("Recu pour " + order.customerEmail() + " : " + total + " centimes");
    }
}
