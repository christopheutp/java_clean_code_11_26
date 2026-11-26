package fr.cleancode.demo.d06_divergent_change.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Divergent Change : notifications — avant.
 */
public class Demo {
    record User(String name) {
    }

    static class NotificationService {
        void sendEmailNotification(User user, String message) {
            System.out.println("Email : " + user.name() + " / " + message);
        }

        void sendSMSNotification(User user, String message) {
            System.out.println("SMS : " + user.name() + " / " + message);
        }

        void sendPushNotification(User user, String message) {
            System.out.println("Push : " + user.name() + " / " + message);
        }
    }

    public static void main(String[] args) {
        var service = new NotificationService();
        var user = new User("Alice");
        service.sendEmailNotification(user, "Bonjour");
        service.sendSMSNotification(user, "Bonjour");
        service.sendPushNotification(user, "Bonjour");
    }
}
