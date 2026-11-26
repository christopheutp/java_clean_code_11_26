package com.example.command;



public class TurnOnCommand implements Command {
    private final Light light;
    public TurnOnCommand(Light light) { this.light = light; }
    @Override public void execute() { light.on(); }
}
