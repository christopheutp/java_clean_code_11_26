package org.example.rpg.item;

import org.example.rpg.model.*;
/** Nouveau comportement : USE_ITEM n'existe pas dans GameEngine */
public final class ManaPotion implements ItemEffect {
    public void use(GameContext c, CombatResult r) { r.spendMana(-30); r.log("Mana potion: +30 mana"); }
}
