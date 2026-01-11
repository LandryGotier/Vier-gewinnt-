/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse.controler;

import tuc.isse.model.Bord;

/**
 * Abstrakte Klasse für den Spielablauf von Vier Gewinnt.
 * Koordiniert Spieler, Spielfeld und Zugwechsel.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public abstract class Game {
    protected final Bord bord;
    protected Player player1;
    protected Player player2;
    protected Player currentPlayer;

    /**
     * Konstruktor initialisiert das Spiel mit einem Spielfeld.
     *
     * @param bord Das Spielfeld
     */
    public Game(Bord bord) {
        this.bord = bord;
    }

    /**
     * Wechselt den aktuellen Spieler.
     */
    protected void swapPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Abstrakte Methode, die den Spielablauf implementiert.
     *
     * @param player1 Spieler 1
     * @param player2 Spieler 2
     */
    public abstract void doGame(Player player1, Player player2);
}
