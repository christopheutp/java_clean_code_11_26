package org.example.rpg.model;

import java.util.ArrayList;
import java.util.List;
/** Résultat mutable pendant le tour ; modifications centralisées et messages observables. */
public class CombatResult {
    private int damage;
    private int health = 200;
    private int mana = 100;
    private boolean critical;
    private boolean cancelled;
    private final List<String> messages = new ArrayList<>();
    public int damage() { return damage; }
    public void damage(int v) { damage = v; }
    public int health() { return health; }
    public void heal(int v) { health += v; }
    public int mana() { return mana; }
    public void spendMana(int v) { mana -= v; }
    public boolean critical() { return critical; }
    public void critical(boolean v) { critical = v; }
    public boolean cancelled() { return cancelled; }
    public void cancel(String reason) { cancelled = true; log(reason); }
    public void log(String s) { messages.add(s); }
    public List<String> messages() { return List.copyOf(messages); }
    public void multiply(double factor) { damage = (int) (damage * factor); }
}
