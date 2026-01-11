package tuc.isse.controler;


import tuc.isse.model.*;
import tuc.isse.view.VierGewinntFrame;

import javax.swing.*;

/**
 * GUI-Spielsteuerung für "Vier gewinnt".
 * Reagiert auf Änderungen im Spielbrett und steuert Spielerwechsel und Spielende.
 */
public class FrameGame extends Game implements BordObserver {

    private final VierGewinntFrame frame;
    private Player currentPlayer;
    private Player player1;
    private Player player2;

    /**
     * Konstruktor. Initialisiert Spielfeld und GUI.
     * @param bord das beobachtete Spielbrett
     * @param frame das GUI-Fenster
     */
    public FrameGame(ObservableBord bord, VierGewinntFrame frame) {
        super(bord);
        this.frame = frame;
        bord.addObserver(this);
    }

    /**
     * Startet das Spiel mit zwei Spielern.
     * Wählt zufällig, wer beginnt, und ruft dessen doTurn() auf.
     */
    @Override
    public void doGame(Player p1, Player p2) {
        this.player1 = p1;
        this.player2 = p2;

        // Zufälliger Startspieler
        currentPlayer = Math.random() < 0.5 ? player1 : player2;
        currentPlayer.doTurn();
    }

    /**
     * Reagiert auf Spielbrettänderungen.
     * Prüft auf Gewinner, Sperrt Buttons oder wechselt Spieler.
     */
    @Override
    public void update(ObservableBord bord) {
        Winner winner = bord.testVictory();

        if (winner != Winner.NONE) {
            // Spielende: Buttons deaktivieren
            frame.removeButtonListener((FramePlayer) currentPlayer);
            // Optional: Gewinner anzeigen
            JOptionPane.showMessageDialog(frame, "Gewonnen hat: " + winner);
        } else {
            // Spieler wechseln und nächsten Zug starten
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
            currentPlayer.doTurn();
        }
    }
}
