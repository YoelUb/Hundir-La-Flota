package com.batallabarcos.barco;

public interface ShipBuilder {
    ShipBuilder setName(String name);
    Ship build();
}
