package fr.cleancode.demo.d03_large_class.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Large Class : compte utilisateur — apres.
 */
public class Demo {
    record Order(String reference) {
    }

    record Message(String text) {
    }

    record Settings(String language) {
    }

    static class UserAccount {
        private String username = "Alice", password = "initial";
        private Settings settings;

        void resetPassword() {
            password = "reset";
        }

        void updateSettings(Settings settings) {
            this.settings = settings;
        }

        String summary() {
            return settings.language() + " / " + password.equals("reset");
        }
    }

    // Chaque service possède les données associées à sa responsabilité.
    static class OrderService {
        private final List<Order> orders = new ArrayList<>();

        void addOrder(Order order) {
            orders.add(order);
        }

        int count() {
            return orders.size();
        }
    }

    static class MessagingService {
        private final List<Message> messages = new ArrayList<>();

        void sendMessage(Message message) {
            messages.add(message);
        }

        int count() {
            return messages.size();
        }
    }

    public static void main(String[] args) {
        var account = new UserAccount();
        var orders = new OrderService();
        var messages = new MessagingService();
        orders.addOrder(new Order("A1"));
        messages.sendMessage(new Message("Bonjour"));
        account.updateSettings(new Settings("fr"));
        account.resetPassword();
        System.out.println(orders.count() + " / " + messages.count() + " / " + account.summary());
    }
}
