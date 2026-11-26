package com.example.strategy;

public class Main {
    public static void main(String[] args) {
        // etape 1 fournir la strategie initiale
        Thermostat thermostat = new Thermostat(new EcoMode());
        thermostat.regulate();

        // etape 2 remplacer l'algo pendant l'execution
        thermostat.setPolicy(new ComfortMode());
        thermostat.regulate();


    }
}
