package org.example.rpg.chain;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
/** Monte une seule fois la chaîne : arme → environnement → résistance → niveau. */
public final class DamagePipeline {
    private final DamageHandler first = new WeaponAndCriticalHandler();
    public DamagePipeline() {
        first.then(new EnvironmentHandler()).then(new ResistanceHandler()).then(new LevelHandler());
    }
    public void apply(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random) { first.apply(c,a,r,random); }
}
