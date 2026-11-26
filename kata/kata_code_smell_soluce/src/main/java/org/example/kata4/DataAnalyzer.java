package org.example.kata4;

import java.util.Map;
import java.util.Objects;

public class DataAnalyzer {
    private final Map<String, DataSource> sources;

    public DataAnalyzer() {
        this(Map.of("database", () -> { /* Lecture depuis la base de données. */ }, "file", () -> { /* Lecture depuis le fichier. */ }, "api", () -> { /* Lecture depuis l’API. */ }));
    }

    public DataAnalyzer(Map<String, DataSource> sources) {
        this.sources = Map.copyOf(sources);
    }

    public void analyzeData(String sourceType) {
        DataSource source = sources.get(Objects.requireNonNull(sourceType));
        if (source == null) {
            System.out.println("Type de source inconnu.");
            return;
        }
        source.read();
        processData();
    }

    private void processData() {
        // Traitement des données.
    }


//    public void analyzeData(String sourceType) {
//        if (sourceType.equals("database")) {
//            // Connexion à la base de données
//            // Lecture des données
//            // Traitement des données
//        } else if (sourceType.equals("file")) {
//            // Ouverture du fichier
//            // Lecture des données
//            // Traitement des données
//        } else if (sourceType.equals("api")) {
//            // Appel de l'API
//            // Lecture des données
//            // Traitement des données
//        } else {
//            System.out.println("Type de source inconnu.");
//        }
//    }
}
