package com.batallabarcos.barco;

public class ShipDirector {

    public Ship buildStandardBattleship(String name) {
        return new BattleshipBuilder().setName(name).build();
    }

    public Ship buildStandardFrigate(String name) {
        return new FrigateBuilder().setName(name).build();
    }

    public Ship buildStandardCanoe(String name) {
        return new CanoeBuilder().setName(name).build();
    }
}

