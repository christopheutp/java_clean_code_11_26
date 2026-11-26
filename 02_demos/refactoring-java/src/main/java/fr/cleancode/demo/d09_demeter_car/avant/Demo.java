package fr.cleancode.demo.d09_demeter_car.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Loi de Déméter : moteur — avant.
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

        FuelInjector getFuelInjector() {
            return fuelInjector;
        }

        SparkPlug getSparkPlug() {
            return sparkPlug;
        }
    }

    static class Car {
        private final Engine engine = new Engine();

        void start() {
            engine.getFuelInjector().injectFuel();
            engine.getSparkPlug().ignite();
        }
    }

    public static void main(String[] args) {
        new Car().start();
    }
}
