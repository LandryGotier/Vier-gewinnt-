/*
 * lk55@tu-clausthal.de
 * Landry Gotier Kuete Yemgie
 * carine.ngon.diboma@tu-clausthal.de
 * Carine Ngon Diboma
 */
package tuc.isse;

import tuc.isse.model.ObservableBord;
import tuc.isse.model.Color;
import tuc.isse.controler.FrameGame;
import tuc.isse.controler.FramePlayer;
import tuc.isse.view.VierGewinntFrame;

/**
 * Startpunkt der GUI-Version von "Vier gewinnt".
 * Initialisiert das Spielbrett, die Oberfläche und beide Spieler.
 */
public class GameMainFrame {

    public static void main(String[] args) {
        // Spielbrett erstellen
        ObservableBord bord = new ObservableBord();

        // GUI-Fenster öffnen
        VierGewinntFrame frame = new VierGewinntFrame(bord);

        // Zwei GUI-Spieler
        FramePlayer player1 = new FramePlayer(Color.RED, bord, frame);
        FramePlayer player2 = new FramePlayer(Color.BLUE, bord, frame);

        // Spielsteuerung starten
        FrameGame game = new FrameGame(bord, frame);
        game.doGame(player1, player2);
    }
}
