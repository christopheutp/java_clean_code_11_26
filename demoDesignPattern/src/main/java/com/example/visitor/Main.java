package com.example.visitor;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : une collection de types concrets différents.
        List<Device> devices = List.of(new Lamp(), new Heater());

        // ÉTAPE B : une opération externalisée : calcul du total en watts.
        EnergyReport report = new EnergyReport();

        // ÉTAPE C : chaque appareil choisit la bonne surcharge visit(...) via accept().
        devices.forEach(device -> device.accept(report));
        System.out.println("Puissance cumulée : " + report.total() + " W");


    }
}
