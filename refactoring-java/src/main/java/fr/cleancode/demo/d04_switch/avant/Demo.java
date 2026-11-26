package fr.cleancode.demo.d04_switch.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Switch Statements : remises — avant.
 */
public class Demo {

    enum ProductType {ELECTRONICS, CLOTHING, FOOD, OTHER}

    record Product(ProductType type, double price) {
        ProductType getType() {
            return type;
        }

        double getPrice() {
            return price;
        }
    }

    static double calculateDiscount(Product product) {
        if (product.getType() == ProductType.ELECTRONICS) return product.getPrice() * 0.1;
        else if (product.getType() == ProductType.CLOTHING) return product.getPrice() * 0.2;
        else if (product.getType() == ProductType.FOOD) return product.getPrice() * 0.05;
        else return 0;
    }

    public static void main(String[] args) {
        for (var type : ProductType.values()) System.out.println(calculateDiscount(new Product(type, 100)));
    }
}
