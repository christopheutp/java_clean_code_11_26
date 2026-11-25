package org.example.rpg.composite;

import org.example.rpg.model.*;
import java.util.List;
/** Exemple d'extension : plusieurs sorts réunis dans une même incantation. */
public final class CombinedSpell implements SpellComponent, org.example.rpg.spell.Spell {
    private final List<SpellComponent> children;
    public CombinedSpell(List<SpellComponent> children) { this.children = List.copyOf(children); }
    public void cast(GameContext c, CombatResult result) {
        int sum = 0;
        for(SpellComponent child : children) {
            CombatResult part = new CombatResult();
            child.cast(c,part);
            sum += part.damage();
            result.heal(part.health()-200);
            result.spendMana(100-part.mana());
            for(String msg : part.messages()) result.log(msg);
        }
        result.damage(sum);
    }
}
