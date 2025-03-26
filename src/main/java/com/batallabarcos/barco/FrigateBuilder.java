package com.batallabarcos.barco;

public class FrigateBuilder implements ShipBuilder {
    private String name;

    @Override
    public ShipBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Ship build() {
        return new Frigate(name);
    }
}
