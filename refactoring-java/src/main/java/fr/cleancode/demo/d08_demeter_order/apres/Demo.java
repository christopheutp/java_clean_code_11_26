package fr.cleancode.demo.d08_demeter_order.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Loi de Déméter : commande — apres.
 */
public class Demo {
    record Account(double balance) {
        double getBalance() {
            return balance;
        }
    }

    record Customer(Account account) {
        double getBalance() {
            return account.getBalance();
        }
    }

    record Order(Customer customer) {
        double getCustomerBalance() {
            return customer.getBalance();
        }
    }

    static class OrderService {
        double getCustomerBalance(Order order) {
            return order.getCustomerBalance();
        }
    }

    public static void main(String[] args) {
        System.out.println(new OrderService().getCustomerBalance(new Order(new Customer(new Account(120)))));
    }
}
