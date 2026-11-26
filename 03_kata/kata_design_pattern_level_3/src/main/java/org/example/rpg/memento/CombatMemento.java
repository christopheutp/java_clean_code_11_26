package org.example.rpg.memento;

import java.util.List;
/** Memento immuable : instantané d'un combat, sans exposer un état modifiable. */
public record CombatMemento(int health,int mana,int xp,String state,List<String> history) {
    public CombatMemento { history = List.copyOf(history); }
}
