package com.batallabarcos.barco;


public class Frigate extends Ship {
    public Frigate(String name) {
        super(name != null ? name : "Frigate", 3);
    }
}
