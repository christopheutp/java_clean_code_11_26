package org.example.soluce;

import java.util.ArrayList;
import java.util.List;

public class CombatResult {

    private int damage;
    private int healthChange;
    private int manaChange;
    private boolean criticalHit;
    private final List<String> messages;

    public CombatResult() {
        this.messages = new ArrayList<>();
        this.healthChange = 0;
        this.manaChange = 0;
        this.damage = 0;
        this.criticalHit = false;
    }


    public int getDamage() {
        return damage;
    }

    public int getHealthChange() {
        return healthChange;
    }

    public int getManaChange() {
        return manaChange;
    }

    public boolean isCriticalHit() {
        return criticalHit;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHealthChange(int healthChange) {
        this.healthChange = healthChange;
    }

    public void setManaChange(int manaChange) {
        this.manaChange = manaChange;
    }

    public void setCriticalHit(boolean criticalHit) {
        this.criticalHit = criticalHit;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
