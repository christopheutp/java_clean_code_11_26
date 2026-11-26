package org.example.rpg.spell;

import org.example.rpg.model.*;
public final class LightningSpell implements Spell {
    public void cast(GameContext c, CombatResult r) {
        int damage = 45 + c.playerLevel() * 4;
        r.spendMana(35); r.log("LIGHTNING BOLT!");
        if ("STORM".equals(c.weather())) { damage = (int)(damage * 1.5); r.log("Storm bonus!"); }
        if ("WATER_ELEMENTAL".equals(c.targetType()) || "METAL_GOLEM".equals(c.targetType())) {
            damage = (int)(damage * 1.8); r.log("Lightning effective!");
        }
        r.damage(damage);
    }
}
