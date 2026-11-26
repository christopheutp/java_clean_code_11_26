package fr.cleancode.demo.d08_demeter_order.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Loi de Déméter : commande — avant.
 */
public class Demo {
    record Account(double balance) {
        double getBalance() {
            return balance;
        }
    }

    record Customer(Account account) {
        Account getAccount() {
            return account;
        }
    }

    record Order(Customer customer) {
        Customer getCustomer() {
            return customer;
        }
    }

    static class OrderService {
        double getCustomerBalance(Order order) {
            return order.getCustomer().getAccount().getBalance();
        }
    }

    public static void main(String[] args) {
        System.out.println(new OrderService().getCustomerBalance(new Order(new Customer(new Account(120)))));
    }
}
