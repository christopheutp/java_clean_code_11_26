package org.example.rpg.chain;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
import org.example.rpg.visitor.EnvironmentVisitor;
/** Étape 2 : combinaison Chain + Visitor (terrain dépend de l'action). */
public final class EnvironmentHandler extends DamageHandler {
    protected void change(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random) {
        a.accept(new EnvironmentVisitor(),c,r);
    }
}
