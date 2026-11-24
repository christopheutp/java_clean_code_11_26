package com.example.memento;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : l'objet possède un état initial.
        Settings settings = new Settings();
        settings.show();

        // ÉTAPE B : mémoriser cet état avant toute modification.
        SettingsSnapshot backup = settings.save();

        // ÉTAPE C : faire varier l'état de l'objet.
        settings.configure(16, true);
        settings.show();

        // ÉTAPE D : restaurer l'état enregistré à l'étape B.
        settings.restore(backup);
        settings.show();


    }
}
