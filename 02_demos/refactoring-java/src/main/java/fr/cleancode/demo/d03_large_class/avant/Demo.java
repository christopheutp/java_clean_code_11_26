package fr.cleancode.demo.d03_large_class.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Large Class : compte utilisateur — avant.
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
        private final List<Order> orders = new ArrayList<>();
        private final List<Message> messages = new ArrayList<>();
        private Settings settings;

        void addOrder(Order order) {
            orders.add(order);
        }

        void sendMessage(Message message) {
            messages.add(message);
        }

        void updateSettings(Settings settings) {
            this.settings = settings;
        }

        void resetPassword() {
            password = "reset";
        }

        String summary() {
            return orders.size() + " / " + messages.size() + " / " + settings.language() + " / " + password.equals("reset");
        }
    }

    public static void main(String[] args) {
        var account = new UserAccount();
        account.addOrder(new Order("A1"));
        account.sendMessage(new Message("Bonjour"));
        account.updateSettings(new Settings("fr"));
        account.resetPassword();
        System.out.println(account.summary());
    }
}
