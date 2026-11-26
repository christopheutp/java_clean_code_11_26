package org.example.rpg.observer;

public final class ConsoleCombatObserver implements CombatObserver {
    public void onEvent(CombatEvent e) { System.out.println("[EVENT " + e.type() + "] " + e.message()); }
}
