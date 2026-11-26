package org.example.rpg.state;

import org.example.rpg.model.CombatResult;
/** Extension niveau 2 : état POISONED. */
public final class PoisonedState implements PlayerState {
    public String name() { return "POISONED"; }
    public void beforeAction(CombatResult result) { result.multiply(.75); result.heal(-5); result.log("Poison state: -25% damage, -5 HP"); }
}
