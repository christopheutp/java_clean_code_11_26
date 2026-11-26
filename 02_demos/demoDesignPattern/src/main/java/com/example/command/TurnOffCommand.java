package com.example.command;



public class TurnOffCommand implements Command {
    private final Light light;
    public TurnOffCommand(Light light) { this.light = light; }
    @Override public void execute() { light.off(); }
}
