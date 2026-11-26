package miniboutique.pricing;

public class VipDiscount implements DiscountPolicy{
    @Override
    public int applyTo(int subtotalCents) {
        return subtotalCents * 90 / 100;
    }
}
