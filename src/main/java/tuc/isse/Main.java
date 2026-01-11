/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse;

import tuc.isse.controler.ConsolePlayer;
import tuc.isse.model.Bord;
import tuc.isse.model.*;

/**
 * Startklasse für das Konsolenspiel Vier Gewinnt.
 * Initialisiert Spielfeld, Spieler und startet das Spiel.
 *
 * @author Landry Gotier Kuete Yemgie und Carine Ngon Diboma
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        Bord bord = new Bord();

        ConsolePlayer player1 = new ConsolePlayer(Color.RED, bord);
        ConsolePlayer player2 = new ConsolePlayer(Color.BLUE, bord);

        ConsoleGame game = new ConsoleGame(bord);
        game.doGame(player1, player2);
    }
}
