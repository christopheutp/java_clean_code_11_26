package miniboutique;

import java.util.List;

public class App {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        Order order = new Order("paul@example.com", List.of(
                new Product("Livre", 2000),
                new Product("Stylo", 500)));

        int total = service.checkout(order, "VIP", "CARD");
        if (total != 2250) throw new AssertionError("Total attendu : 2250");
        if (!service.history().equals(List.of("paul@example.com:2250"))) {
            throw new AssertionError("Historique incorrect");
        }
        System.out.println("Verification OK");
    }
}
