package fr.cleancode.demo.d04_switch.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Switch Statements : remises — apres.
 */
public class Demo {

    abstract static class Product {
        protected final double price;

        Product(double price) {
            this.price = price;
        }

        abstract double calculateDiscount();

        double getPrice() {
            return price;
        }
    }

    // Chaque produit porte sa propre règle de remise.
    static class Electronics extends Product {
        Electronics(double p) {
            super(p);
        }

        double calculateDiscount() {
            return price * 0.1;
        }
    }

    static class Clothing extends Product {
        Clothing(double p) {
            super(p);
        }

        double calculateDiscount() {
            return price * 0.2;
        }
    }

    static class Food extends Product {
        Food(double p) {
            super(p);
        }

        double calculateDiscount() {
            return price * 0.05;
        }
    }

    static class Other extends Product {
        Other(double p) {
            super(p);
        }

        double calculateDiscount() {
            return 0;
        }
    }

    public static void main(String[] args) {
        for (var product : List.of(new Electronics(100), new Clothing(100), new Food(100), new Other(100)))
            System.out.println(product.calculateDiscount());
    }
}
