package org.example.rpg.composite;

import org.example.rpg.model.*;
/** Composite : une feuille ou un groupe de sorts expose la même opération. */
public interface SpellComponent { void cast(GameContext c, CombatResult r); }
