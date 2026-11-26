package fr.cleancode.demo.d05_temporary_field.avant;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Temporary Field : générateur de rapports — avant.
 */
public class Demo {
    static Connection connection() {
        return (Connection) java.lang.reflect.Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class<?>[]{Connection.class}, (proxy, method, args) -> {
            throw new UnsupportedOperationException("Connexion de démonstration");
        });
    }

    static class ReportGenerator {
        private final String title, content, footer;
        private Connection databaseConnection; // Inutile pour un rapport standard.

        ReportGenerator(String title, String content, String footer) {
            this.title = title;
            this.content = content;
            this.footer = footer;
        }

        void setDatabaseConnection(Connection connection) {
            this.databaseConnection = connection;
        }

        void generate() {
            System.out.println(title + " / " + content + " / " + footer);
            if (databaseConnection != null) System.out.println("Rapport avec accès à la base");
        }
    }

    public static void main(String[] args) {
        var report = new ReportGenerator("Titre", "Contenu", "Fin");
        report.generate();
        report.setDatabaseConnection(connection());
        report.generate();
    }
}
