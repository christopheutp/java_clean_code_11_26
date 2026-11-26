package com.example.composite;



public class Lamp implements Controllable {
    private final String name;
    public Lamp(String name) { this.name = name; }
    @Override public void activate() { System.out.println("Allumer " + name); }
}
