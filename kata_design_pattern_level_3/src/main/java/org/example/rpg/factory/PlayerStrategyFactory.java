package org.example.rpg.factory;

import org.example.rpg.strategy.*;
/** Simple Factory : centralise le choix de la stratégie. */
public final class PlayerStrategyFactory {
    public PlayerStrategy create(String type) {
        return switch(type) {
            case "WARRIOR" -> new WarriorStrategy();
            case "MAGE" -> new MageStrategy();
            case "ROGUE" -> new RogueStrategy();
            default -> throw new IllegalArgumentException("Unknown class: " + type);
        };
    }
}
