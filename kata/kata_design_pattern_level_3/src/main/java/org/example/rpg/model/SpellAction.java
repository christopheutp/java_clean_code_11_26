package org.example.rpg.model;

import org.example.rpg.visitor.ActionVisitor;
public final class SpellAction implements CombatAction {
    @Override public ActionKind kind() { return ActionKind.CAST_SPELL; }
    @Override public void accept(ActionVisitor v, GameContext c, CombatResult r) { v.visitSpell(c,r); }
}
