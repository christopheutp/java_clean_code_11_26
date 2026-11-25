package org.example.rpg.factory;

import org.example.rpg.spell.*;
import org.example.rpg.composite.*;
import java.util.List;
/** Factory : ordre de priorité des parchemins identique au sujet original. */
public final class SpellFactory {
    public Spell fromInventory(List<String> items) {
        if(items.contains("FIREBALL_SCROLL")) return new FireballSpell();
        if(items.contains("LIGHTNING_SCROLL")) return new LightningSpell();
        if(items.contains("HEAL_SCROLL")) return new HealSpell();
        // Extension du niveau 3 : sort composé.
        if(items.contains("COMBO_SCROLL")) return new CombinedSpell(List.of(
            new AtomicSpell(new FireballSpell()), new AtomicSpell(new LightningSpell())));
        return null;
    }
}
