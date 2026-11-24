package com.example.command;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : créer le récepteur capable d'exécuter l'action.
        Light light = new Light();

        // ÉTAPE B : encapsuler plusieurs intentions, sans les exécuter encore.
        List<Command> planning = List.of(
                new TurnOnCommand(light),
                new TurnOffCommand(light)
        );

        // ÉTAPE C : exécuter les ordres plus tard via leur contrat commun.
        planning.forEach(Command::execute);


    }
}
