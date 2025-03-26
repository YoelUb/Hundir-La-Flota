package com.batallabarcos.barco;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship {
    protected String name;
    protected int size;
    protected List<Boolean> hitPositions;

    protected Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hitPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) hitPositions.add(false);
    }

    public void hit(int position) {
        if (position >= 0 && position < size) {
            hitPositions.set(position, true);
        }
    }

    public boolean isSunk() {
        return hitPositions.stream().allMatch(Boolean::booleanValue);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<Boolean> getHitStatus() {
        return hitPositions;
    }
}
