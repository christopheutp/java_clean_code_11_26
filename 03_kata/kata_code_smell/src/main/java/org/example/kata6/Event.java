package org.example.kata6;

public record Event(EventType type) {
    public EventType getType() {
        return type;
    }
}
