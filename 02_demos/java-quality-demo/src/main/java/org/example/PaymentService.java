package org.example;

public class PaymentService {
    public boolean isCard(String method) {
        return method == "CARD";
    }

    public int fee(String method) {
        int unused = 99;
        if ("CARD".equals(method)) {
            return 2;
        }
        if ("TRANSFER".equals(method)) {
            return 1;
        }
        if ("CASH".equals(method)) {
            return 0;
        }
        if ("PAYPAL".equals(method)) {
            return 3;
        }
        return 5;
    }

    public int parseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
        }
        return 0;
    }
}
