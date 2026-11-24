package com.example.memento;



public class Settings {
    private int temperature = 20;
    private boolean awayMode;
    public SettingsSnapshot save() { return new SettingsSnapshot(temperature, awayMode); }
    public void restore(SettingsSnapshot snapshot) {
        temperature = snapshot.temperature();
        awayMode = snapshot.awayMode();
    }
    public void configure(int temperature, boolean awayMode) {
        this.temperature = temperature;
        this.awayMode = awayMode;
    }
    public void show() { System.out.println("Température=" + temperature + ", absence=" + awayMode); }
}
