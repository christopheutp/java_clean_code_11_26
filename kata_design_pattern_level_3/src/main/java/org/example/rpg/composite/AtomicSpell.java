package org.example.rpg.composite;

import org.example.rpg.model.*;
import org.example.rpg.spell.Spell;
public record AtomicSpell(Spell spell) implements SpellComponent {
    public void cast(GameContext c, CombatResult r) { spell.cast(c,r); }
}
