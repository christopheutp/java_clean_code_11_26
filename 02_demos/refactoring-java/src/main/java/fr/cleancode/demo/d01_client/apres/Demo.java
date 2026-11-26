package fr.cleancode.demo.d01_client.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * ClientService : refactoring legacy — apres.
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

    static class Validator {
        boolean isValid(Client c) {
            return c.getName() != null && c.getAge() > 0;
        }
    }

    static class CreditScoreCalculator {
        int calculate(Client c) {
            return score(c);
        }
    }

    static class ClientService {
        private final Validator validator;
        private final CreditScoreCalculator creditScoreCalculator;
        private final EmailService emailService;
        private final Database database;

        // Les collaborateurs sont fournis par l'appelant, ce qui facilite les tests.
        ClientService(Validator validator, CreditScoreCalculator calculator, EmailService emailService, Database database) {
            this.validator = validator;
            this.creditScoreCalculator = calculator;
            this.emailService = emailService;
            this.database = database;
        }

        void processClientData(Client client) {
            validateClient(client);
            int creditScore = creditScoreCalculator.calculate(client);
            emailService.sendWelcomeEmail(client.getEmail());
            database.updateClientRecord(client);
        }

        private void validateClient(Client client) {
            if (!validator.isValid(client)) throw new IllegalArgumentException("Données client invalides");
        }
    }

    public static void main(String[] args) {
        var service = new ClientService(new Validator(), new CreditScoreCalculator(), new EmailService(), new Database());
        service.processClientData(new Client("Alice", 30, "alice@example.org"));
        try {
            service.processClientData(new Client(null, 0, ""));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
