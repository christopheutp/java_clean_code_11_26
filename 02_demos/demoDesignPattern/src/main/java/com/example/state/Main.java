package com.example.state;


public class Main {
    public static void main(String[] args) {
        // ÉTAPE A : l'alarme démarre à l'état désarmé.
        Alarm alarm = new Alarm();
        alarm.movement(); // Mouvement ignoré.

        // ÉTAPE B : on passe à l'état armé.
        alarm.setState(new ArmedState());
        alarm.movement(); // Sirène déclenchée ; l'état devient TriggeredState.

        // ÉTAPE C : même événement, mais désormais autre comportement.
        alarm.movement(); // Sirène déjà active.
        alarm.status();   // Déclenchée.


    }
}
