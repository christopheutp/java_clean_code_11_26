package com.example.composite;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : créer les feuilles (équipements individuels).
        Lamp salon = new Lamp("Salon");
        Lamp cuisine = new Lamp("Cuisine");
        Lamp entree = new Lamp("Entrée");

        // ÉTAPE B : créer une pièce contenant des feuilles.
        Room rezDeChaussee = new Room().add(salon).add(cuisine);

        // ÉTAPE C : construire un groupe qui contient lui-même un autre groupe.
        Room maison = new Room().add(rezDeChaussee).add(entree);

        // ÉTAPE D : un seul appel agit sur tous les éléments, récursivement.
        maison.activate();


    }
}
