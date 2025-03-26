package com.batallabarcos.juego;

import java.util.Scanner;

public class GameManager {
    private final Player player1;
    private final Player player2;
    private final Scanner scanner = new Scanner(System.in);

    public GameManager(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public void startGame() {
        System.out.println("¡Comienza el juego!");
        int turno = 1;

        while (true) {
            System.out.println("\n----- Turno " + turno + " -----");

            // Turno jugador 1
            System.out.println(player1.getName() + ", elige coordenadas para atacar:");
            int[] coords1 = pedirCoordenadas();
            player1.attack(player2, coords1[0], coords1[1]);

            // Turno jugador 2
            System.out.println(player2.getName() + ", elige coordenadas para atacar:");
            int[] coords2 = pedirCoordenadas();
            player2.attack(player1, coords2[0], coords2[1]);

            boolean p1Lost = player1.hasLost();
            boolean p2Lost = player2.hasLost();

            if (p1Lost && p2Lost) {
                System.out.println("¡Empate!");
                break;
            } else if (p1Lost) {
                System.out.println(player2.getName() + " gana!");
                break;
            } else if (p2Lost) {
                System.out.println(player1.getName() + " gana!");
                break;
            }

            turno++;
        }
    }

    private int[] pedirCoordenadas() {
        int x, y;
        while (true) {
            try {
                System.out.print("Introduce coordenada X (0-9): ");
                x = Integer.parseInt(scanner.nextLine());
                System.out.print("Introduce coordenada Y (0-9): ");
                y = Integer.parseInt(scanner.nextLine());
                if (x >= 0 && x < 10 && y >= 0 && y < 10) break;
                else System.out.println("Coordenadas fuera de rango.");
            } catch (Exception e) {
                System.out.println("Entrada no válida. Intenta de nuevo.");
            }
        }
        return new int[]{x, y};
    }
}
