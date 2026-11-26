package org.example.rpg.strategy;

import org.example.rpg.model.*;
import org.example.rpg.factory.SpellFactory;
import org.example.rpg.spell.Spell;
import java.util.function.DoubleSupplier;
public final class MageStrategy implements PlayerStrategy {
    private final SpellFactory spells = new SpellFactory();
    public void attack(GameContext c, CombatResult r, DoubleSupplier random) {
        int damage = 30 + c.playerLevel() * 4;
        r.spendMana(25);
        if (c.has("STAFF_OF_FIRE")) {
            damage += 20; r.log("Flames engulf enemy!");
            if ("FOREST".equals(c.environment())) { damage += 10; r.log("Forest catches fire!"); }
        } else if (c.has("ICE_WAND")) {
            damage += 15; r.log("Ice crystals form!");
            if ("SNOW".equals(c.weather())) { damage += 8; r.log("Blizzard bonus!"); }
        }
        if (r.mana() < 0) { r.cancel("Not enough mana!"); return; }
        r.damage(damage);
    }
    public void castSpell(GameContext c, CombatResult r, DoubleSupplier random) {
        Spell spell = spells.fromInventory(c.playerItems());
        if (spell != null) spell.cast(c, r);
        // Sinon le programme d'origine continue avec 0 dégât.
    }
}
