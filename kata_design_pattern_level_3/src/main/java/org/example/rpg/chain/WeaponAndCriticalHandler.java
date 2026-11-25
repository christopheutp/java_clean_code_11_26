package org.example.rpg.chain;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
/** Premier maillon : les bonus d'arme ont été extraits dans Strategy, qui les calcule
 * avant ce maillon. Le critique du WARRIOR est ici, juste avant les modificateurs
 * d'environnement */
public final class WeaponAndCriticalHandler extends DamageHandler {
    protected void change(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random) {
        if (a.kind()==ActionKind.ATTACK && "WARRIOR".equals(c.playerClass())
                && random.getAsDouble() > .8) {
            r.multiply(2);
            r.critical(true);
            r.log("CRITICAL HIT!");
        }
    }
}
