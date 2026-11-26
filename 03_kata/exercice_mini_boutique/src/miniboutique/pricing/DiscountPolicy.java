package miniboutique.pricing;

public interface DiscountPolicy {
    int applyTo(int subtotalCents);
}
