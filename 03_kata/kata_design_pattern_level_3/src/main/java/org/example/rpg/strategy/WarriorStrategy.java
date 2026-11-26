package org.example.rpg.strategy;

import org.example.rpg.model.*;
import org.example.rpg.composite.*;
import java.util.List;
import java.util.function.DoubleSupplier;
public final class WarriorStrategy implements PlayerStrategy {
    public void attack(GameContext c, CombatResult r, DoubleSupplier random) {
        int damage = 50 + c.playerLevel() * 3;
        if (c.has("LEGENDARY_SWORD")) { damage += 25; r.log("Legendary sword glows!"); }
        else if (c.has("MAGIC_AXE")) {
            damage += 15;
            if (random.getAsDouble() > .7) { damage = (int)(damage * 1.5); r.log("Magic axe lightning!"); }
        } else if (c.has("RUSTY_BLADE")) {
            damage += 5;
            if (random.getAsDouble() > .9) { damage = 1; r.log("Rusty blade breaks!"); }
        }
        r.damage(damage);
        // Extension niveau 3 : arme combinant plusieurs enchantements.
        if (c.has("ENCHANTED_SET")) new WeaponSet(List.of(
            new EnchantedWeapon("Fire enchantment", 8),
            new EnchantedWeapon("Ice enchantment", 4))).apply(r);
    }
    public void castSpell(GameContext c, CombatResult r, DoubleSupplier random) {
        if (!c.has("RAGE_POTION")) { r.cancel("Warriors need rage potion!"); return; }
        r.damage((50 + c.playerLevel() * 3) * 2);
        r.heal(-10);
        r.log("BERSERKER RAGE!");
    }
}
