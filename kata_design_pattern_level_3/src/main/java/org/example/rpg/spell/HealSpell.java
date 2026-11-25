package org.example.rpg.spell;

import org.example.rpg.model.*;
public final class HealSpell implements Spell {
    public void cast(GameContext c, CombatResult r) {
        r.heal(50 + c.playerLevel() * 2);
        r.spendMana(20);
        r.damage(0);
        r.log("HEAL!");
    }
}
