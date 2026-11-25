package org.example.rpg.model;

import org.example.rpg.visitor.ActionVisitor;
/** Visitor (niveau 3) : une action accepte un traitement sans modifier sa classe. */
public interface CombatAction {
    ActionKind kind();
    void accept(ActionVisitor visitor, GameContext context, CombatResult result);
}
