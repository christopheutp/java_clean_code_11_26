package fr.cleancode.demo.d01_client.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * ClientService : refactoring legacy — avant.
 */
public class Demo {
    record Client(String name, int age, String email) {
        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }

        String getEmail() {
            return email;
        }
    }

    static class EmailService {
        void sendWelcomeEmail(String email) {
            System.out.println("Bienvenue : " + email);
        }
    }

    static class Database {
        void updateClientRecord(Client client) {
            System.out.println("Client enregistré : " + client.name());
        }
    }

    // Score fictif, suffisant pour illustrer la séparation des responsabilités.
    static int score(Client client) {
        return client.age() * 2;
    }

    static class ClientService {
        void processClientData(Client client) {
            // Validation, calcul, email et stockage sont réunis ici.
            if (client.getName() != null && client.getAge() > 0) {
                int creditScore = calculateCreditScore(client);
                EmailService emailService = new EmailService();
                emailService.sendWelcomeEmail(client.getEmail());
                Database database = new Database();
                database.updateClientRecord(client);
            } else throw new IllegalArgumentException("Données client invalides");
        }

        int calculateCreditScore(Client client) {
            return score(client);
        }
    }

    public static void main(String[] args) {
        var service = new ClientService();
        service.processClientData(new Client("Alice", 30, "alice@example.org"));
        try {
            service.processClientData(new Client(null, 0, ""));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
