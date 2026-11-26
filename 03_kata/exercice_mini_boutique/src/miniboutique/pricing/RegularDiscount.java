package miniboutique.pricing;

public class RegularDiscount implements DiscountPolicy {

    @Override
    public int applyTo(int subtotalCents) {
        return subtotalCents;
    }
}
