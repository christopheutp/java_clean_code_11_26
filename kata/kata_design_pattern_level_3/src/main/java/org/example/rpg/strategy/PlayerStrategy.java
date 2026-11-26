package org.example.rpg.strategy;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
/** Strategy : le comportement dépend de la classe du personnage. */
public interface PlayerStrategy {
    void attack(GameContext c, CombatResult r, DoubleSupplier random);
    void castSpell(GameContext c, CombatResult r, DoubleSupplier random);
}
