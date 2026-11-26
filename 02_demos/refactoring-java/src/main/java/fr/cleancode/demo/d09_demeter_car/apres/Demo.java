package fr.cleancode.demo.d09_demeter_car.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Loi de Déméter : moteur — apres.
 */
public class Demo {
    static class FuelInjector {
        void injectFuel() {
            System.out.println("Injection de carburant");
        }
    }

    static class SparkPlug {
        void ignite() {
            System.out.println("Allumage");
        }
    }

    static class Engine {
        private final FuelInjector fuelInjector = new FuelInjector();
        private final SparkPlug sparkPlug = new SparkPlug();

        void start() {
            fuelInjector.injectFuel();
            sparkPlug.ignite();
        }
    }

    static class Car {
        private final Engine engine = new Engine();

        void start() {
            engine.start();
        }
    }

    public static void main(String[] args) {
        new Car().start();
    }
}
