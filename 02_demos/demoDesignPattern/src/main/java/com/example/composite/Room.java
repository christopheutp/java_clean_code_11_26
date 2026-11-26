package com.example.composite;




import java.util.ArrayList;
import java.util.List;

public class Room implements Controllable {
    private final List<Controllable> children = new ArrayList<>();
    public Room add(Controllable child) { children.add(child); return this; }
    @Override public void activate() {
        for (Controllable child : children) child.activate();
    }
}
