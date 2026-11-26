package org.example.rpg.command;

import org.example.rpg.model.*;
import org.example.rpg.strategy.PlayerStrategy;
import java.util.function.DoubleSupplier;
public final class AttackCommand implements GameCommand {
    private final GameContext c;
    private final PlayerStrategy strategy;
    private final DoubleSupplier random;
    public AttackCommand(GameContext c,PlayerStrategy s,DoubleSupplier rng) { this.c=c; strategy=s; random=rng; }
    public CombatResult execute() { CombatResult r = new CombatResult(); strategy.attack(c,r,random); return r; }
    public CombatAction action() { return new AttackAction(); }
}
