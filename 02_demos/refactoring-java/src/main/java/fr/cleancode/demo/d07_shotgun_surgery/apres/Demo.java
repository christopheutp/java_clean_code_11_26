package fr.cleancode.demo.d07_shotgun_surgery.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Shotgun Surgery : format de date — apres.
 */
public class Demo {
    static Date sample() {
        return new GregorianCalendar(2025, Calendar.SEPTEMBER, 11).getTime();
    }

    static class DateUtil {
        private static final String PATTERN = "dd/MM/yyyy";

        static String formatDate(Date date) {
            return new SimpleDateFormat(PATTERN).format(date);
        }
    }

    record Order(Date orderDate) {
        String getFormattedDate() {
            return DateUtil.formatDate(orderDate);
        }
    }

    record Invoice(Date invoiceDate) {
        String getFormattedDate() {
            return DateUtil.formatDate(invoiceDate);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Order(sample()).getFormattedDate());
        System.out.println(new Invoice(sample()).getFormattedDate());
    }
}
