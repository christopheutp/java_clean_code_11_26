package fr.cleancode.demo.d06_divergent_change.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Divergent Change : notifications — apres.
 */
public class Demo {
    record User(String name) {
    }

    interface NotificationStrategy {
        void send(User user, String message);
    }

    static class EmailNotification implements NotificationStrategy {
        public void send(User u, String m) {
            System.out.println("Email : " + u.name() + " / " + m);
        }
    }

    static class SMSNotification implements NotificationStrategy {
        public void send(User u, String m) {
            System.out.println("SMS : " + u.name() + " / " + m);
        }
    }

    static class PushNotification implements NotificationStrategy {
        public void send(User u, String m) {
            System.out.println("Push : " + u.name() + " / " + m);
        }
    }

    static class NotificationService {
        private final NotificationStrategy strategy;

        NotificationService(NotificationStrategy strategy) {
            this.strategy = strategy;
        }

        void sendNotification(User user, String message) {
            strategy.send(user, message);
        }
    }

    public static void main(String[] args) {
        var user = new User("Alice");
        for (var strategy : List.of(new EmailNotification(), new SMSNotification(), new PushNotification()))
            new NotificationService(strategy).sendNotification(user, "Bonjour");
    }
}
