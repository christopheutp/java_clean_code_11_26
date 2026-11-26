package org.example.rpg.strategy;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
public final class RogueStrategy implements PlayerStrategy {
    public void attack(GameContext c, CombatResult r, DoubleSupplier random) {
        int damage = 35 + c.playerLevel() * 2;
        if (random.getAsDouble() > .6) { damage = (int)(damage * 1.8); r.log("Sneak attack!"); }
        if (c.has("POISON_DAGGERS")) { damage += 12; r.log("Poison dagger!"); }
        else if (c.has("SHADOW_BLADES")) {
            damage += 18;
            if ("CAVE".equals(c.environment()) || "DUNGEON".equals(c.environment())) {
                damage += 10; r.log("Shadows empower blades!");
            }
        }
        r.damage(damage);
    }
    public void castSpell(GameContext c, CombatResult r, DoubleSupplier random) {
        if (!c.has("SMOKE_BOMB")) { r.cancel("Rogues need special items!"); return; }
        r.log("Smoke bomb diversion!");
        if (random.getAsDouble() > .5) { r.damage((35 + c.playerLevel() * 2) * 2); r.log("Surprise attack!"); }
        else { r.damage(0); r.log("Missed in the smoke!"); }
    }
}
