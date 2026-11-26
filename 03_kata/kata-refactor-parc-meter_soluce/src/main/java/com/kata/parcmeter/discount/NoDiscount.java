package com.kata.parcmeter.discount;

import com.kata.parcmeter.model.Price;

public class NoDiscount implements DiscountCalculator {
    @Override
    public Price apply(Price basePrice) {
        return basePrice;
    }
}
