package fr.cleancode.demo.d05_temporary_field.apres;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;

/**
 * Temporary Field : générateur de rapports — apres.
 */
public class Demo {
    static Connection connection() {
        return (Connection) java.lang.reflect.Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class<?>[]{Connection.class}, (proxy, method, args) -> {
            throw new UnsupportedOperationException("Connexion de démonstration");
        });
    }

    static class ReportGenerator {
        protected final String title, content, footer;

        ReportGenerator(String title, String content, String footer) {
            this.title = title;
            this.content = content;
            this.footer = footer;
        }

        void generate() {
            System.out.println(title + " / " + content + " / " + footer);
        }
    }

    static class DatabaseReportGenerator extends ReportGenerator {
        private final Connection databaseConnection;

        DatabaseReportGenerator(String title, String content, String footer, Connection connection) {
            super(title, content, footer);
            this.databaseConnection = Objects.requireNonNull(connection);
        }

        @Override
        void generate() {
            super.generate();
            System.out.println("Rapport avec accès à la base");
        }
    }

    public static void main(String[] args) {
        new ReportGenerator("Titre", "Contenu", "Fin").generate();
        new DatabaseReportGenerator("Titre", "Contenu", "Fin", connection()).generate();
    }
}
