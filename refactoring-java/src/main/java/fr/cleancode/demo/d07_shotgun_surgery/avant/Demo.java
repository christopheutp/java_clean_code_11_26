package fr.cleancode.demo.d07_shotgun_surgery.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Shotgun Surgery : format de date — avant.
 */
public class Demo {
    static Date sample() {
        return new GregorianCalendar(2025, Calendar.SEPTEMBER, 11).getTime();
    }

    record Order(Date orderDate) {
        String getFormattedDate() {
            return new SimpleDateFormat("dd/MM/yyyy").format(orderDate);
        }
    }

    record Invoice(Date invoiceDate) {
        String getFormattedDate() {
            return new SimpleDateFormat("dd/MM/yyyy").format(invoiceDate);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Order(sample()).getFormattedDate());
        System.out.println(new Invoice(sample()).getFormattedDate());
    }
}
