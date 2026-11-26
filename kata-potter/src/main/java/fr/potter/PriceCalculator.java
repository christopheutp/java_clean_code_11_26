package fr.potter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PriceCalculator {
    private static final int BOOK_PRICE = 800;
    private static final int[] DISCOUNTS = {0, 0, 5, 10, 20, 25};

    public int calculatePrice(BookBasket basket) {
        return findMinimumPrice(basket.getQuantities(), new HashMap<>());
    }


    private int findMinimumPrice(int[] quantities, Map<String, Integer> cache) {
        if (Arrays.stream(quantities).allMatch(q -> q == 0)) {
            return 0;
        }
        String key = Arrays.toString(quantities);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int minimum = Integer.MAX_VALUE;

        for (int mask = 1; mask < (1 << quantities.length); mask++) {
            int[] remaining = quantities.clone();
            int groupSize = 0;
            boolean valid = true;
            for (int i = 0; i < remaining.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (remaining[i] == 0) {
                        valid = false;
                        break;
                    }
                    remaining[i]--;
                    groupSize++;
                }
            }
            if (valid) {
                int total = groupPrice(groupSize) + findMinimumPrice(remaining, cache);
                minimum = Math.min(minimum, total);
            }
        }
        cache.put(key, minimum);
        return minimum;
    }


    private int groupPrice(int size) {
        return size * BOOK_PRICE * (100 - DISCOUNTS[size]) / 100;
    }
}
