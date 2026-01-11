/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse.controler;

import tuc.isse.model.*;

/**
 * Abstrakte Spielerklasse für das Spiel Vier Gewinnt.
 * Verwaltet Spielerfarbe, Spielfeld und allgemeine Zuglogik.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public abstract class Player {
    public final Color color;
    protected final Bord bord;

    public Color getColor() {
        return color;
    }

    public Bord getBord() {
        return bord;
    }

    /**
     * Konstruktor: Initialisiert Spieler mit Farbe und Spielfeld.
     *
     * @param color Spielerfarbe
     * @param bord Spielfeld
     */
    public Player(Color color, Bord bord) {
        this.color = color;
        this.bord = bord;
    }

    /**
     * Führt einen Zug durch, indem ein Spielstein in die angegebene Spalte geworfen wird.
     * Behandelt dabei auch mögliche Ausnahmen.
     *
     * @param columnIndex Spaltenindex (0-6)
     */
    protected void doDrop (int columnIndex) {
        try {
            Token token = bord.getToken(color);
            if (token == null) {
                System.out.println("Keine Spielsteine mehr für Spieler: " + color);
                return;
            }
            bord.dropToken(token, columnIndex);
        } catch (ColumnFullException | IllegalMoveException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }


    /**
     * Abstrakte Methode zur Ausführung eines Spielzugs.
     * Muss in Unterklassen implementiert werden.
     */
    public abstract void doTurn();
}
