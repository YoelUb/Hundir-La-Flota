package com.batallabarcos;

import com.batallabarcos.barco.*;
import com.batallabarcos.tablero.*;
import com.batallabarcos.puerto.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTests {

    @Test
    public void testBattleshipCreation() {
        ShipDirector director = new ShipDirector();
        Ship battleship = director.buildStandardBattleship("Destructor");
        assertEquals(5, battleship.getSize(), "Battleship debe tener tamaño 5");
    }

    @Test
    public void testShipHitAndSunk() {
        ShipDirector director = new ShipDirector();
        Ship canoe = director.buildStandardCanoe("Canoa");
        assertFalse(canoe.isSunk());
        canoe.hit(0);
        assertTrue(canoe.isSunk(), "La canoa debe estar hundida tras un impacto");
    }

    @Test
    public void testBoardShotRegistration() {
        Board board = new Board();
        ShipDirector director = new ShipDirector();
        Ship canoe = director.buildStandardCanoe("Canoa");
        boolean placed = board.placeShip(canoe, 0, 0, true);
        assertTrue(placed, "La canoa debe colocarse correctamente");

        boolean hit = board.receiveAttack(0, 0);
        assertTrue(hit, "Debe haber impacto en la canoa");
        assertTrue(canoe.isSunk(), "La canoa debe estar hundida tras el disparo");
    }

    @Test
    public void testGraphConnections() {
        GrafoPuertos grafo = new GrafoPuertos();

        grafo.agregarPuerto("Alpha");
        grafo.agregarPuerto("Beta");
        grafo.agregarPuerto("Gamma");

        grafo.conectar("Alpha", "Beta", 3);
        grafo.conectar("Alpha", "Gamma", 2);

        // Solo verificamos que no lanza excepción y que la DFS imprime los nodos
        grafo.dfs("Alpha");
    }
}
