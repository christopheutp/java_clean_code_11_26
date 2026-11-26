package com.example.observer;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : créer le sujet qui émet les événements.
        TemperatureSensor sensor = new TemperatureSensor();

        // ÉTAPE B : inscrire deux observateurs au même sujet.
        sensor.subscribe(degrees -> System.out.println("Écran : " + degrees + " °C"));
        sensor.subscribe(degrees -> {
            if (degrees > 28) System.out.println("Alerte chaleur !");
        });

        // ÉTAPE C : déclencher l'événement ; les deux abonnés sont notifiés.
        sensor.measure(30);


    }
}
