package com.example.exo1;

public class SubscriptionServiceSoluce {

    public void register(String email, int months) {
        validateSubscription(email, months);
        int price = calculatePrice(months);
        printConfirmation(email, price);
    }

    private void validateSubscription(String email, int months) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email required");
        }
        if (months <= 0) {
            throw new IllegalArgumentException("Invalid duration");
        }
    }

    private int calculatePrice(int months) {
        return months * 12;
    }

    private void printConfirmation(String email, int price) {
        System.out.println("Subscription: " + email + " / " + price + " EUR");
    }


}
