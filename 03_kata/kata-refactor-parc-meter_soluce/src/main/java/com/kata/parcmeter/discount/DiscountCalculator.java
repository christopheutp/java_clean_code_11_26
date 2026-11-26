package com.kata.parcmeter.discount;

import com.kata.parcmeter.model.Price;

public interface DiscountCalculator {
    Price apply(Price basePrice);
}
