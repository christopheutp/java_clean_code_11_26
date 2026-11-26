package fr.cleancode.demo.d02_long_method.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Long Method : rapport de transactions — apres.
 */
public class Demo {
    record Transaction(int id, double amount, LocalDate date, boolean suspicious) {
        int getId() {
            return id;
        }

        double getAmount() {
            return amount;
        }

        LocalDate getDate() {
            return date;
        }

        boolean isSuspicious() {
            return suspicious;
        }
    }

    static void alertFraud(Transaction t) {
        System.out.println("Alerte fraude : " + t.getId());
    }

    static List<Transaction> sample() {
        return List.of(new Transaction(1, 100, LocalDate.of(2025, 9, 11), false), new Transaction(2, 50, LocalDate.of(2025, 9, 11), true));
    }

    // La méthode principale décrit les étapes du traitement.
    static void generateReport(List<Transaction> transactions) {
        double total = calculateTotal(transactions);
        displayTransactions(transactions);
        System.out.println("Total Amount: " + total);
    }

    private static double calculateTotal(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    private static void displayTransactions(List<Transaction> transactions) {
        for (Transaction t : transactions) {
            displayTransactionDetails(t);
            checkForFraud(t);
        }
    }

    private static void displayTransactionDetails(Transaction t) {
        System.out.println("Transaction ID: " + t.getId());
        System.out.println("Amount: " + t.getAmount());
        System.out.println("Date: " + t.getDate());
    }

    private static void checkForFraud(Transaction t) {
        if (t.isSuspicious()) alertFraud(t);
    }

    public static void main(String[] args) {
        generateReport(sample());
    }
}
