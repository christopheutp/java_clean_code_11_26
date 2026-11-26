package org.example.rpg.spell;

import org.example.rpg.model.*;
public final class FireballSpell implements Spell {
    public void cast(GameContext c, CombatResult r) {
        int damage = 40 + c.playerLevel() * 5;
        r.spendMana(30); r.log("FIREBALL!");
        if ("PLANT_MONSTER".equals(c.targetType()) || "TREE_SPIRIT".equals(c.targetType())) {
            damage *= 2; r.log("Fire effective on plants!");
        }
        r.damage(damage);
    }
}
