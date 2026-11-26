package org.example.rpg.state;

public final class StateFactory {
    public PlayerState create(String name) {
        return switch(name) {
            case "RAGE" -> new RageState();
            case "STEALTH" -> new StealthState();
            case "POISONED" -> new PoisonedState();
            default -> new NormalState();
        };
    }
}
