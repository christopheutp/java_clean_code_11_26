package org.example.rpg.state;

import org.example.rpg.model.CombatResult;
/** Extension niveau 2 : état NORMAL. */
public final class NormalState implements PlayerState {
    public String name() { return "NORMAL"; }
    public void beforeAction(CombatResult result) { /* Aucun changement. */ }
}
