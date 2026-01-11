/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse.controler;

import tuc.isse.model.Bord;
import tuc.isse.model.Color;

/**
 * Simulierter Spieler für automatische Tests.
 * Führt vorgegebene Züge ohne Nutzereingabe aus.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public class MocPlayer extends Player {
    private final int moveFirstColumn;
    private final int moveSecondColumn;
    private int round;

    /**
     * Konstruktor initialisiert einen automatisierten Spieler.
     *
     * @param color Spielerfarbe
     * @param bord Spielfeld
     * @param moveFirstColumn Spalte für gerade Runden
     * @param moveSecondColumn Spalte für ungerade Runden
     * @param round Startwert der Runde (meist 0)
     */
    public MocPlayer(Color color, Bord bord, int moveFirstColumn, int moveSecondColumn, int round) {
        super(color, bord);
        this.moveFirstColumn = moveFirstColumn;
        this.moveSecondColumn = moveSecondColumn;
        this.round = round;
    }

    /**
     * Führt automatisch einen Zug aus, je nach Rundenanzahl.
     */
    @Override
    public void doTurn() {
        if (round % 2 == 0) {
            doDrop(moveFirstColumn);
        } else {
            doDrop(moveSecondColumn);
        }
        round++;
    }
}
