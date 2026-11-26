package org.example.rpg.state;

import org.example.rpg.model.CombatResult;
/** Extension niveau 2 : état STEALTH. */
public final class StealthState implements PlayerState {
    public String name() { return "STEALTH"; }
    public void beforeAction(CombatResult result) { result.multiply(1.25); result.log("Stealth state: +25% damage"); }
}
