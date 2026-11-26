package org.example.kata4;

public class DataAnalyzer {
    public void analyzeData(String sourceType) {
        if (sourceType.equals("database")) {
            // Connexion à la base de données
            // Lecture des données
            // Traitement des données
        } else if (sourceType.equals("file")) {
            // Ouverture du fichier
            // Lecture des données
            // Traitement des données
        } else if (sourceType.equals("api")) {
            // Appel de l'API
            // Lecture des données
            // Traitement des données
        } else {
            System.out.println("Type de source inconnu.");
        }
    }
}
