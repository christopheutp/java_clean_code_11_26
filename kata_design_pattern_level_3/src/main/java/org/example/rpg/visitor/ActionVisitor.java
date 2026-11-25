package org.example.rpg.visitor;

import org.example.rpg.model.*;
/** Visitor : opérations variant selon le type d'action. */
public interface ActionVisitor {
    void visitAttack(GameContext c, CombatResult r);
    void visitSpell(GameContext c, CombatResult r);
    void visitItem(GameContext c, CombatResult r);
}
