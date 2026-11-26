package org.example.rpg.command;

import org.example.rpg.model.*;
import org.example.rpg.strategy.PlayerStrategy;
import java.util.function.DoubleSupplier;
public final class CastSpellCommand implements GameCommand {
    private final GameContext c;
    private final PlayerStrategy strategy;
    private final DoubleSupplier random;
    public CastSpellCommand(GameContext c,PlayerStrategy s,DoubleSupplier rng) { this.c=c; strategy=s; random=rng; }
    public CombatResult execute() { CombatResult r = new CombatResult(); strategy.castSpell(c,r,random); return r; }
    public CombatAction action() { return new SpellAction(); }
}
