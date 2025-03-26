package com.batallabarcos.barco;

public class Canoe extends Ship {
    public Canoe(String name) {
        super(name != null ? name : "Canoe", 1);
    }
}
