package org.example.rpg.memento;

import java.util.ArrayDeque;
import java.util.Deque;
/** Caretaker : stocke les instantanés sans manipuler leur contenu. */
public final class CombatHistory {
    private final Deque<CombatMemento> checkpoints = new ArrayDeque<>();
    public void checkpoint(CombatSession session) { checkpoints.push(session.save()); }
    public void undo(CombatSession session) {
        if (checkpoints.isEmpty()) throw new IllegalStateException("No checkpoint");
        session.restore(checkpoints.pop());
    }
}
