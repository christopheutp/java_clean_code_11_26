package org.example.soluce.strategy;

import org.example.soluce.CombatResult;
import org.example.soluce.GameContext;

public interface PlayerClassStrategy {
    CombatResult executeAction(String actionType, GameContext context);
}
