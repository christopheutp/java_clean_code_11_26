package org.example.rpg.chain;

import org.example.rpg.model.*;
import java.util.function.DoubleSupplier;
/** Chain of Responsibility : chaque maillon applique une étape et passe au suivant. */
public abstract class DamageHandler {
    private DamageHandler next;
    public DamageHandler then(DamageHandler successor) { next = successor; return successor; }
    public final void apply(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random) {
        change(c, a, r, random);
        if (next != null) next.apply(c,a,r,random);
    }
    protected abstract void change(GameContext c, CombatAction a, CombatResult r, DoubleSupplier random);
}
