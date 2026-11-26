package org.example.rpg.chain;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
/** Étape 4 : modificateur de niveau appliqué APRÈS résistance. */
public final class LevelHandler extends DamageHandler {
    protected void change(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random) {
        if(c.targetLevel() > c.playerLevel()) { r.multiply(.9); r.log("Enemy level advantage"); }
        else if(c.playerLevel() > c.targetLevel() + 2) { r.multiply(1.2); r.log("Player level advantage"); }
    }
}
