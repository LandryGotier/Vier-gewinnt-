/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse;

import tuc.isse.controler.Game;
import tuc.isse.controler.Player;
import tuc.isse.model.Bord;
import tuc.isse.model.Winner;

/**
 * Konkrete Spielklasse für die Konsole.
 * Führt ein Spiel zwischen zwei Spielern interaktiv durch.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public class ConsoleGame extends Game {

    /**
     * Konstruktor ruft den Konstruktor der Oberklasse auf.
     *
     * @param bord das Spielfeld
     */
    public ConsoleGame(Bord bord) {
        super(bord);
    }

    /**
     * Führt den Spielablauf durch:
     * - Spieler setzen
     * - Abwechselnd Spielzüge
     * - Spielfeld anzeigen
     * - Auf Sieg oder Unentschieden prüfen
     *
     * @param player1 Spieler 1
     * @param player2 Spieler 2
     */
    @Override
    public void doGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        System.out.println("Spiel beginnt!");

        while (true) {
            System.out.println(bord); // Aktuelles Spielfeld anzeigen
            System.out.println("Spieler " + currentPlayer.color + " ist am Zug.");
            currentPlayer.doTurn(); // Spieler führt einen Zug durch

            // Nach jedem Zug prüfen, ob jemand gewonnen hat
            Winner winner = bord.testVictory();
            if (winner != Winner.NONE) {
                System.out.println(bord);
                System.out.println("Spieler " + winner + " hat gewonnen!");
                break;
            }

            // Optional: Unentschieden prüfen (alle Spalten voll)
            boolean full = true;
            for (int col = 0; col < bord.cols; col++) {
                if (bord.canDrop(col)) {
                    full = false;
                    break;
                }
            }
            if (full) {
                System.out.println(bord);
                System.out.println("Unentschieden!");
                break;
            }

            swapPlayer(); // Spieler wechseln
        }
    }
}
