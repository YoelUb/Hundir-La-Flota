package com.batallabarcos.barco;

public class Battleship extends Ship {
    public Battleship(String name) {
        super(name != null ? name : "Battleship", 5);
    }
}
