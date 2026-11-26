package org.example.kata7;

import java.util.Map;

public class UserManager {
    private final Map<String, Runnable> permissions = Map.of("admin", this::grantAdminPermissions, "editor", this::grantEditorPermissions, "viewer", this::grantViewerPermissions);

    public void createUser(String username, String password, String role) {
        validateUser(username, password);
        saveUser(username, password);
        assignRole(role);
    }

    private void validateUser(String username, String password) {
        // Validation des données.
    }

    private void saveUser(String username, String password) {
        // Création de l’utilisateur dans la base de données.
    }

    private void assignRole(String role) {
        Runnable grant = permissions.get(role);
        if (grant == null) {
            System.out.println("Rôle inconnu.");
            return;
        }
        grant.run();
    }

    private void grantAdminPermissions() {
        // Donner toutes les permissions.
    }

    private void grantEditorPermissions() {
        // Donner les permissions d’édition.
    }

    private void grantViewerPermissions() {
        // Donner les permissions de visualisation.
    }
}
