package org.example.rpg.factory;

import org.example.rpg.item.*;
/** Factory des nouveaux objets utilisables (extension du niveau 1). */
public final class ItemFactory {
    public ItemEffect create(String name) {
        return switch(name) {
            case "MANA_POTION" -> new ManaPotion();
            case "HEALING_POTION" -> new HealingPotion();
            default -> throw new IllegalArgumentException("Unknown usable item: " + name);
        };
    }
}
