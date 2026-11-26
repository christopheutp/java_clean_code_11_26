package org.example.rpg.item;

import org.example.rpg.model.*;
public final class HealingPotion implements ItemEffect {
    public void use(GameContext c, CombatResult r) { r.heal(40); r.log("Healing potion: +40 HP"); }
}
