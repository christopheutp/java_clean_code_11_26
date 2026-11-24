package miniboutique.pricing;

import miniboutique.Order;
import miniboutique.Product;

public class PriceCalculator {
    public int total(Order order, DiscountPolicy discount) {
        int subtotal = 0;
        for (Product product : order.products()) {
            subtotal += product.unitPriceCents();
        }
        return discount.applyTo(subtotal);
    }
}
