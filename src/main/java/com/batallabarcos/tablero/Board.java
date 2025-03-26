package com.batallabarcos.tablero;

import com.batallabarcos.barco.Ship;

public class Board {
    private final int SIZE = 10;
    private final Cell[][] grid;

    public Board() {
        grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                grid[i][j] = new Cell();
    }

    public boolean placeShip(Ship ship, int x, int y, boolean horizontal) {
        int size = ship.getSize();
        if (horizontal) {
            if (x + size > SIZE) return false;
            for (int i = 0; i < size; i++)
                if (grid[x + i][y].hasShip()) return false;
            for (int i = 0; i < size; i++)
                grid[x + i][y].placeShip(ship, i);
        } else {
            if (y + size > SIZE) return false;
            for (int i = 0; i < size; i++)
                if (grid[x][y + i].hasShip()) return false;
            for (int i = 0; i < size; i++)
                grid[x][y + i].placeShip(ship, i);
        }
        return true;
    }

    public boolean receiveAttack(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        Cell cell = grid[x][y];
        return cell.receiveAttack();
    }

    public int getSize() {
        return SIZE;
    }
}
