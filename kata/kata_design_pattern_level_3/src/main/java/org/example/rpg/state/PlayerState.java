package org.example.rpg.state;

import org.example.rpg.model.CombatResult;
/** State : le comportement dépend de l'état du joueur. */
public interface PlayerState {
    String name();
    void beforeAction(CombatResult result);
}
