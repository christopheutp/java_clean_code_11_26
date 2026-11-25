package org.example.rpg.observer;

import java.util.ArrayList;
import java.util.List;
/** Observer : le moteur publie, des abonnés réagissent sans couplage. */
public final class CombatEventBus {
    private final List<CombatObserver> listeners = new ArrayList<>();
    public void subscribe(CombatObserver o) { listeners.add(o); }
    public void publish(String type, String message) {
        CombatEvent event = new CombatEvent(type, message);
        for (CombatObserver observer : listeners) observer.onEvent(event);
    }
}
