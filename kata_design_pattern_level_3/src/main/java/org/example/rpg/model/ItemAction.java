package org.example.rpg.model;

import org.example.rpg.visitor.ActionVisitor;
public final class ItemAction implements CombatAction {
    @Override public ActionKind kind() { return ActionKind.USE_ITEM; }
    @Override public void accept(ActionVisitor v, GameContext c, CombatResult r) { v.visitItem(c,r); }
}
