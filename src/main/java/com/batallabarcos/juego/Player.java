package com.batallabarcos.juego;

import com.batallabarcos.barco.Ship;
import com.batallabarcos.tablero.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    public final String name;
    public final List<Ship> ships;
    public final Board board;

    public Player(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
        this.board = new Board();
    }

    public void addShip(Ship ship, int x, int y, boolean horizontal) {
        if (board.placeShip(ship, x, y, horizontal)) {
            ships.add(ship);
        }
    }

    public boolean hasLost() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    public boolean receiveAttack(int x, int y) {
        return board.receiveAttack(x, y);
    }

    public void attack(Player opponent) {
        Random rand = new Random();
        int size = board.getSize();
        int x = rand.nextInt(size);
        int y = rand.nextInt(size);
        opponent.receiveAttack(x, y);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void attack(Player opponent, int x, int y) {
        boolean acierto = opponent.receiveAttack(x, y);
        System.out.println("Ataque a (" + x + "," + y + "): " + (acierto ? "¡ACIERTO!" : "agua..."));
    }

}
