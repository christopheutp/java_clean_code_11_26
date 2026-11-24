package com.example.factory;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : demander un capteur, sans écrire new DoorSensor().
        Sensor door = SensorFactory.create("door");
        System.out.println(door.read());

        // ÉTAPE B : même méthode pour une autre variante.
        Sensor smoke = SensorFactory.create("smoke");
        System.out.println(smoke.read());


    }
}
