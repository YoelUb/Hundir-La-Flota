package com.batallabarcos.tablero;

import com.batallabarcos.barco.Ship;

public class Cell {
    private Ship ship;
    private boolean hit;
    private int positionInShip;

    public Cell() {
        this.ship = null;
        this.hit = false;
        this.positionInShip = -1;
    }

    public boolean isHit() {
        return hit;
    }

    public Ship getShip() {
        return ship;
    }

    public void placeShip(Ship ship, int positionInShip) {
        this.ship = ship;
        this.positionInShip = positionInShip;
    }

    public boolean receiveAttack() {
        if (hit) return false;
        this.hit = true;
        if (ship != null) {
            ship.hit(positionInShip);
            return true;
        }
        return false;
    }

    public boolean hasShip() {
        return ship != null;
    }
}
