package org.example.rpg.state;

import org.example.rpg.model.CombatResult;
/** Extension niveau 2 : état RAGE. */
public final class RageState implements PlayerState {
    public String name() { return "RAGE"; }
    public void beforeAction(CombatResult result) { result.multiply(2.0); result.heal(-10); result.log("Rage state: x2 damage, -10 HP"); }
}
