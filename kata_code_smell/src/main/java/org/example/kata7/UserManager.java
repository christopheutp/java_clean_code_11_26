package org.example.kata7;

public class UserManager {
    public void createUser(String username, String password, String role) {
        // Validation des données
        // Création de l'utilisateur dans la base de données
        // Attribution du rôle
        if (role.equals("admin")) {
            // Donner toutes les permissions
        } else if (role.equals("editor")) {
            // Donner les permissions d'édition
        } else if (role.equals("viewer")) {
            // Donner les permissions de visualisation
        } else {
            System.out.println("Rôle inconnu.");
        }
    }
}
