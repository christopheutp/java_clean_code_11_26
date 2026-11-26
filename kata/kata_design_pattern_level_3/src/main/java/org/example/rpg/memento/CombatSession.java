package org.example.rpg.memento;

import java.util.ArrayList;
import java.util.List;
/** Originator : seul cet objet connaît et restaure ses champs. */
public final class CombatSession {
    private int health = 200, mana = 100, xp;
    private String state = "NORMAL";
    private final List<String> history = new ArrayList<>();
    public int health() {return health;}
    public int mana() {return mana;}
    public int xp() {return xp;}
    public String state() {return state;}
    public List<String> history() { return List.copyOf(history); }
    public void setState(String state) { this.state=state; }
    public void change(int hp,int mp,int experience,String event) {
        health+=hp; mana+=mp; xp+=experience; history.add(event);
    }
    public CombatMemento save() {return new CombatMemento(health,mana,xp,state,history);}
    public void restore(CombatMemento snapshot) {
        health=snapshot.health(); mana=snapshot.mana(); xp=snapshot.xp(); state=snapshot.state();
        history.clear(); history.addAll(snapshot.history());
    }
}
