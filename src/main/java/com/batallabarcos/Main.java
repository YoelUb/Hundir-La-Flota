package com.batallabarcos;

import com.batallabarcos.barco.Ship;
import com.batallabarcos.barco.ShipDirector;
import com.batallabarcos.juego.GameManager;
import com.batallabarcos.juego.Player;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Jugador 1");
        Player p2 = new Player("Jugador 2");

        ShipDirector director = new ShipDirector();

        // Jugador 1: colocar en filas diferentes
        Ship b1 = director.buildStandardBattleship("Titan");
        Ship f1 = director.buildStandardFrigate("Falcon");
        Ship c1 = director.buildStandardCanoe("Mini");
        p1.addShip(b1, 0, 0, true);   // horizontal fila 0
        p1.addShip(f1, 0, 1, true);   // horizontal fila 1
        p1.addShip(c1, 0, 2, true);   // horizontal fila 2

        // Jugador 2: colocar en columnas diferentes
        Ship b2 = director.buildStandardBattleship("Kraken");
        Ship f2 = director.buildStandardFrigate("Ghost");
        Ship c2 = director.buildStandardCanoe("Dot");
        p2.addShip(b2, 0, 0, false);  // vertical columna 0
        p2.addShip(f2, 1, 0, false);  // vertical columna 1
        p2.addShip(c2, 2, 0, false);  // vertical columna 2

        GameManager game = new GameManager(p1, p2);
        game.startGame();
    }
}
