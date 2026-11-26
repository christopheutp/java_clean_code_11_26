package org.example.rpg.model;

import org.example.rpg.visitor.ActionVisitor;
public final class AttackAction implements CombatAction {
    @Override public ActionKind kind() { return ActionKind.ATTACK; }
    @Override public void accept(ActionVisitor v, GameContext c, CombatResult r) { v.visitAttack(c,r); }
}
