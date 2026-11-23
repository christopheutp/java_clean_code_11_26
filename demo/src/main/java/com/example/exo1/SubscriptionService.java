package com.example.exo1;

public class SubscriptionService {
    public void register(String email, int months) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email required");
        }
        if (months <= 0) {
            throw new IllegalArgumentException("Invalid duration");
        }
        int price = months * 12;
        System.out.println("Subscription: " + email + " / " + price + " EUR");
    }

    /*Refactorez register pour qu'elle exprime clairement les étapes de l'opération.
    Conservez le même comportement. Vous pouvez extraire des méthodes privées.*/
}
