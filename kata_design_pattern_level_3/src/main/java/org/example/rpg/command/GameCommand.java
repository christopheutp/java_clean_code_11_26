package org.example.rpg.command;

import org.example.rpg.model.*;
public interface GameCommand {
    CombatResult execute();
    CombatAction action();
}
